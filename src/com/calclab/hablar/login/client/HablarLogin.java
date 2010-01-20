package com.calclab.hablar.login.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;

public class HablarLogin implements EntryPoint {

    public static void install(HablarWidget hablar) {
	createLoginPage(hablar, Suco.get(Session.class));
    }

    private static void createLoginPage(HablarWidget hablar, Session session) {
	final LoginView login = new LoginPage(Visibility.open);
	final Pages pages = hablar.getPages();
	pages.add(login);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(Session session) {
		if (session.getState() == State.disconnected) {
		    pages.open(login);
		}
	    }
	});
    }

    @Override
    public void onModuleLoad() {
    }

}
