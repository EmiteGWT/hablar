package com.calclab.hablar.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.client.login.LoginWidget;
import com.calclab.hablar.client.pages.PagesPanel;
import com.calclab.hablar.client.roster.RosterWidget;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class HablarLogic {

    private final boolean hasLogin;
    private final LoginWidget loginWidget;
    private final boolean hasRoster;
    private RosterWidget rosterWidget;

    public HablarLogic(final PagesPanel hablar) {
	Session session = Suco.get(Session.class);

	Listener<String> statusListener = new Listener<String>() {
	    @Override
	    public void onEvent(String status) {
		hablar.setStatus(status);
	    }
	};

	/* we always have the panel, to see the status, but not always visible */
	loginWidget = new LoginWidget();
	loginWidget.onStatusChanged(statusListener);
	hasLogin = !PageAssist.getMeta("hablar.loginWidget").equals("false");
	if (hasLogin) {
	    hablar.add(loginWidget, true);
	}

	hasRoster = !PageAssist.getMeta("hablar.rosterWidget").equals("false");
	if (hasRoster) {
	    rosterWidget = new RosterWidget();
	    rosterWidget.onStatusChanged(statusListener);
	    hablar.add(rosterWidget, true);
	}

	session.onStateChanged(new Listener<State>() {
	    @Override
	    public void onEvent(State state) {
		if (hasRoster && state == State.ready) {
		    hablar.show(rosterWidget);
		} else if (hasLogin && state == State.disconnected) {
		    hablar.show(loginWidget);
		}
	    }
	});

    }
}
