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
import com.google.gwt.core.client.GWT;

public class HablarLogin implements EntryPoint {

    private static LoginMessages loginMessages;

    public static LoginMessages i18n() {
	return loginMessages;
    }

    public static void install(final Hablar hablar) {
	install(hablar, LoginConfig.getFromMeta());
    }

    public static void install(final Hablar hablar, final LoginConfig config) {
	final Session session = Suco.get(Session.class);
	final LoginPage login = new LoginPage(hablar.getEventBus(), new LoginWidget());
	hablar.addPage(login);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		setState(login, session);
	    }
	});
	setState(login, session);
	login.getDisplay().getUser().setText(config.userName);
	login.getDisplay().getPassword().setText(config.password);
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar(), LoginConfig.getFromMeta());
    }

    public static void setMessages(final LoginMessages messages) {
	loginMessages = messages;
    }

    private static void setState(final Page<?> login, final Session session) {
	if (session.getState() == State.disconnected) {
	    login.requestVisibility(Visibility.focused);
	}
    }

    @Override
    public void onModuleLoad() {
	LoginMessages messages = (LoginMessages) GWT.create(LoginMessages.class);
	setMessages(messages);
	LoginWidget.setMessages(messages);

    }

}
