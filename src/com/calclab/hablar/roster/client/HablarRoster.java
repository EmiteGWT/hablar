package com.calclab.hablar.roster.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.basic.client.Hablar;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarRoster implements EntryPoint {

    public static void createLoginPage(final Hablar hablar, boolean isDocked, Session session) {
	GWT.log("INSTALL ROSTER", null);
	boolean isReady = session.getState() == State.ready;
	Visibility visibility = isReady ? Visibility.focused : Visibility.notFocused;

	final RosterView rosterPage = new RosterPageWidget(hablar.getEventBus(), visibility);

	final Pages pages = hablar.getPages();
	if (isDocked) {
	    hablar.setDocked(rosterPage, 200);
	} else {
	    hablar.getPages().add(rosterPage);
	}

	session.onStateChanged(new Listener<Session>() {

	    @Override
	    public void onEvent(Session session) {
		if (session.getState() == State.ready) {
		    GWT.log("SHOW ROSTER", null);
		    pages.open(rosterPage);
		}
	    }
	});
    }

    public static void install(final Hablar hablar, boolean isDocked) {
	Session session = Suco.get(Session.class);
	createLoginPage(hablar, isDocked, session);
    }

    @Override
    public void onModuleLoad() {

    }

}
