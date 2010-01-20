package com.calclab.hablar.roster.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.basic.client.ui.HablarView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;

public class HablarRoster implements EntryPoint {

    public static void install(final HablarView hablar, boolean isDocked) {
	final Pages pages = hablar.getPages();
	final RosterView rosterPage = new RosterPageWidget(Visibility.open);
	if (isDocked) {
	    hablar.setDocked(rosterPage, 200);
	} else {
	    hablar.getPages().add(rosterPage);
	}
	Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {

	    @Override
	    public void onEvent(Session session) {
		if (session.getState() == State.ready) {
		    pages.open(rosterPage);
		}
	    }
	});
    }

    @Override
    public void onModuleLoad() {

    }

}
