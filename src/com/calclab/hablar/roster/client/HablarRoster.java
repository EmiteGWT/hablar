package com.calclab.hablar.roster.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;

public class HablarRoster implements EntryPoint {

    public static void createLoginPage(final Hablar hablarPresenter, Session session) {
	final RosterPresenter roster = new RosterPresenter(hablarPresenter.getEventBus(), new RosterWidget());
	roster.setVisibility(Visibility.notFocused);
	hablarPresenter.addPage(roster);

	session.onStateChanged(new Listener<Session>() {

	    @Override
	    public void onEvent(Session session) {
		setState(roster, session.getState());
	    }
	});
	setState(roster, session.getState());
    }

    public static void install(HablarWidget widget) {
	createLoginPage(widget.getHablar(), Suco.get(Session.class));
    }

    private static void setState(final RosterPresenter roster, State state) {
	if (state == State.ready) {
	    roster.requestOpen();
	}
    }

    @Override
    public void onModuleLoad() {

    }

}
