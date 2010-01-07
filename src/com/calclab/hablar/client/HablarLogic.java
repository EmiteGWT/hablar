package com.calclab.hablar.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.client.login.LoginWidget;
import com.calclab.hablar.client.pages.PagesPanel;
import com.calclab.hablar.client.roster.RosterPage;
import com.calclab.hablar.client.search.SearchPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener0;

public class HablarLogic {
    private final LoginWidget loginWidget;
    private RosterPage rosterPage;
    private final PagesPanel hablar;
    private final HablarConfig config;

    public HablarLogic(HablarConfig config, final PagesPanel hablar) {
	this.config = config;
	this.hablar = hablar;
	final Session session = Suco.get(Session.class);

	/* we always have the panel, to see the status, but not always visible */
	loginWidget = new LoginWidget();
	if (config.hasLogin) {
	    hablar.add(loginWidget, true);
	}

	if (config.hasRoster) {
	    rosterPage = new RosterPage();
	    hablar.add(rosterPage, true);
	}

	if (config.hasSearch) {
	    SearchPage searchPage = new SearchPage();
	    hablar.add(searchPage, false);
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
	    hablar.show(rosterPage);
	} else if (config.hasLogin && state == State.disconnected) {
	    hablar.show(loginWidget);
	}
    }

}
