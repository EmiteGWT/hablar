package com.calclab.hablar.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.client.login.LoginPage;
import com.calclab.hablar.client.roster.RosterPageWidget;
import com.calclab.hablar.client.search.SearchPageWidget;
import com.calclab.hablar.client.ui.HablarView;
import com.calclab.hablar.client.ui.page.Page.Visibility;
import com.calclab.hablar.client.ui.pages.Pages;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HablarLogic {
    public static final String SEARCH_ICON = "HablarLogic-searchAction";

    private final LoginPage loginPage;
    private RosterPageWidget rosterPageWidget;
    private final HablarConfig config;
    private final Pages pages;

    public HablarLogic(final HablarConfig config, HablarView hablarView, final Pages pages) {
	this.config = config;
	this.pages = pages;
	final Session session = Suco.get(Session.class);

	// FIXME: no longer necessary
	/* we always have the panel, to see the status, but not always visible */
	loginPage = new LoginPage(Visibility.open);
	if (config.hasLogin) {
	    pages.add(loginPage);
	}

	if (config.hasRoster) {
	    rosterPageWidget = new RosterPageWidget(Visibility.closed);
	    addSearchPage(config, pages);
	    if ("left".equals(config.dockRoster)) {
		hablarView.addDocked(rosterPageWidget);
	    } else {
		pages.add(rosterPageWidget);
	    }
	}
	hablarView.init();

	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		setState(session.getState());
	    }

	});
	setState(session.getState());
    }

    private void addSearchPage(final HablarConfig config, final Pages pages) {
	if (config.hasSearch) {
	    final SearchPageWidget searchPageWidget = new SearchPageWidget(Visibility.hidden);
	    pages.add(searchPageWidget);
	    rosterPageWidget.addAction(HablarStyles.get(HablarStyles.IconType.search), SEARCH_ICON, new ClickHandler() {
		@Override
		public void onClick(final ClickEvent event) {
		    pages.open(searchPageWidget);
		}
	    });
	}
    }

    private void setState(final State state) {
	if (config.hasRoster && state == State.ready) {
	    pages.open(rosterPageWidget);
	} else if (config.hasLogin && state == State.disconnected) {
	    pages.open(loginPage);
	}
    }

}
