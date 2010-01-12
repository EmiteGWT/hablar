package com.calclab.hablar.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.client.login.LoginPage;
import com.calclab.hablar.client.roster.RosterPage;
import com.calclab.hablar.client.search.SearchPage;
import com.calclab.hablar.client.ui.pages.Pages;
import com.calclab.hablar.client.ui.pages.Pages.Position;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener0;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HablarLogic {
    private final LoginPage loginPage;
    private RosterPage rosterPage;
    private final HablarConfig config;
    private final Pages pages;

    public HablarLogic(HablarConfig config, final Pages pages) {
	this.config = config;
	this.pages = pages;
	final Session session = Suco.get(Session.class);

	/* we always have the panel, to see the status, but not always visible */
	loginPage = new LoginPage();
	if (config.hasLogin) {
	    pages.add(loginPage, Pages.Position.normal);
	}

	if (config.hasRoster) {
	    rosterPage = new RosterPage();
	    pages.add(rosterPage, Pages.Position.WEST);
	    if (config.hasSearch) {
		final SearchPage searchPage = new SearchPage();
		pages.add(searchPage, Pages.Position.normal);
		rosterPage.addAction(searchPage.icons.searchIcon(), new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {
			pages.open(searchPage);
		    }
		});
	    }
	}

	session.onStateChanged(new Listener0() {
	    @Override
	    public void onEvent() {
		setState(session.getState());
	    }

	});
	setState(session.getState());
    }

    private void setState(State state) {
	if (config.hasRoster && state == State.ready) {
	    pages.open(rosterPage);
	} else if (config.hasLogin && state == State.disconnected) {
	    pages.open(loginPage);
	}
    }

}
