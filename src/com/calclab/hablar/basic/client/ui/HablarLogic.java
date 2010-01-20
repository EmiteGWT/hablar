package com.calclab.hablar.basic.client.ui;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class HablarLogic {
    public static final String SEARCH_ICON = "HablarLogic-searchAction";

    private final Pages pages;
    private final HablarView view;

    public HablarLogic(HablarView view) {
	this.view = view;
	this.pages = view.getPages();
	final Session session = Suco.get(Session.class);

	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		setState(session.getState());
	    }

	});
	setState(session.getState());
    }

    private void setState(final State state) {
	if (view.hasRoster() && state == State.ready) {
	    pages.open(view.getRosterPage());
	} else if (view.hasLogin() && state == State.disconnected) {
	    pages.open(view.getLoginPage());
	}
    }

}
