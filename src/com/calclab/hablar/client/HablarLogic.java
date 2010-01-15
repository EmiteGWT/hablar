package com.calclab.hablar.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.client.login.LoginPage;
import com.calclab.hablar.client.roster.RosterPageWidget;
import com.calclab.hablar.client.search.SearchPageWidget;
import com.calclab.hablar.client.ui.page.Page.Visibility;
import com.calclab.hablar.client.ui.pages.Pages;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HablarLogic {
    private final LoginPage loginPage;
    private RosterPageWidget rosterPageWidget;
    private final HablarConfig config;
    private final Pages pages;

    public HablarLogic(HablarConfig config, final Pages pages) {
	this.config = config;
	this.pages = pages;
	final Session session = Suco.get(Session.class);

	/* we always have the panel, to see the status, but not always visible */
	loginPage = new LoginPage();
	if (config.hasLogin) {
	    pages.add(loginPage, Visibility.open);
	}

	if (config.hasRoster) {
	    rosterPageWidget = new RosterPageWidget();
	    pages.add(rosterPageWidget, Visibility.closed);
	    if (config.hasSearch) {
		final SearchPageWidget searchPageWidget = new SearchPageWidget();
		pages.add(searchPageWidget, Visibility.hidden);
		rosterPageWidget.addAction(HablarStyles.get(HablarStyles.IconType.search), new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {
			pages.open(searchPageWidget);
		    }
		});
	    }
	}

	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(Session session) {
		setState(session.getState());
	    }

	});
	setState(session.getState());
    }

    private void setState(State state) {
	if (config.hasRoster && state == State.ready) {
	    pages.open(rosterPageWidget);
	} else if (config.hasLogin && state == State.disconnected) {
	    pages.open(loginPage);
	}
    }

}
