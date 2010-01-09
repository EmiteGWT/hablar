package com.calclab.hablar.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.client.login.LoginWidget;
import com.calclab.hablar.client.pages.PagesWidget;
import com.calclab.hablar.client.roster.RosterPage;
import com.calclab.hablar.client.search.SearchPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener0;

public class HablarLogic {
    private final LoginWidget loginWidget;
    private RosterPage rosterPage;
    private final HablarConfig config;
    private final PagesWidget pages;

    public HablarLogic(HablarConfig config, final PagesWidget pages) {
	this.config = config;
	this.pages = pages;
	final Session session = Suco.get(Session.class);

	/* we always have the panel, to see the status, but not always visible */
	loginWidget = new LoginWidget();
	if (config.hasLogin) {
	    pages.add(loginWidget, true);
	}

	if (config.hasRoster) {
	    rosterPage = new RosterPage();
	    pages.add(rosterPage, true);
	}

	if (config.hasSearch) {
	    SearchPage searchPage = new SearchPage();
	    pages.add(searchPage, false);
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
	    pages.show(rosterPage);
	} else if (config.hasLogin && state == State.disconnected) {
	    pages.show(loginWidget);
	}
    }

}
