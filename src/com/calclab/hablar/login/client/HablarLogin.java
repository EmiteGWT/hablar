package com.calclab.hablar.login.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;

public class HablarLogin implements EntryPoint {

    public static void install(Hablar hablar) {
	install(hablar, LoginConfig.getFromMeta());
    }

    public static void install(Hablar hablar, LoginConfig config) {
	Session session = Suco.get(Session.class);
	final LoginPage login = new LoginPage(hablar.getEventBus(), new LoginWidget());
	hablar.addPage(login);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(Session session) {
		setState(login, session);
	    }
	});
	setState(login, session);
	login.getDisplay().getUser().setText(config.userName);
	login.getDisplay().getPassword().setText(config.password);
    }

    public static void install(HablarWidget widget) {
	install(widget.getHablar(), LoginConfig.getFromMeta());
    }

    private static void setState(final Page<?> login, Session session) {
	if (session.getState() == State.disconnected) {
	    login.requestVisibility(Visibility.focused);
	}
    }

    @Override
    public void onModuleLoad() {

    }

}
