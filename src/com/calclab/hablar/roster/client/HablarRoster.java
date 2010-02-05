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

    public static void install(final Hablar hablarPresenter) {

	final RosterPresenter roster = new RosterPresenter(hablarPresenter.getEventBus(), new RosterWidget());
	roster.setVisibility(Visibility.notFocused);
	hablarPresenter.addPage(roster);

	new RosterBasicActions(roster);

	Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {

	    @Override
	    public void onEvent(Session session) {
		setState(roster, session.getState());
	    }
	});
	setState(roster, session.getState());
    }

    public static void install(HablarWidget widget) {
	install(widget.getHablar());
    }

    private static void setState(final RosterPresenter roster, State state) {
	if (state == State.ready) {
	    roster.requestFocus();
	}
    }

    @Override
    public void onModuleLoad() {

    }

}
