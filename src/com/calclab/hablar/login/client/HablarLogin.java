package com.calclab.hablar.login.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;

public class HablarLogin implements EntryPoint {

    public static void install(HablarWidget widget) {
	LoginConfig config = LoginConfig.getFromMeta();
	createLoginPage(widget.getHablar(), config, Suco.get(Session.class));
    }

    private static void createLoginPage(Hablar hablarPresenter, LoginConfig config, Session session) {
	final LoginPresenter login = new LoginPresenter(hablarPresenter.getEventBus(), new LoginWidget());
	hablarPresenter.addPage(login);
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

    private static void setState(final Page<?> login, Session session) {
	if (session.getState() == State.disconnected) {
	    login.requestOpen();
	}
    }

    @Override
    public void onModuleLoad() {

    }

}
