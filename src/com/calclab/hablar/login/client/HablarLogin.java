package com.calclab.hablar.login.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.basic.client.ui.HablarView;
import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarLogin implements EntryPoint {

    public static void install(HablarWidget hablar) {
	createLoginPage(hablar, Suco.get(Session.class));
    }

    private static void createLoginPage(HablarView hablar, Session session) {
	boolean isDisconnected = session.getState() == State.disconnected;
	Visibility visibility = isDisconnected ? Visibility.open : Visibility.closed;
	final LoginView login = new LoginPage(visibility);
	final Pages pages = hablar.getPages();
	pages.add(login);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(Session session) {
		if (session.getState() == State.disconnected) {
		    GWT.log("SHOW LOGIN", null);
		    pages.open(login);
		}
	    }
	});
    }

    @Override
    public void onModuleLoad() {
    }

}
