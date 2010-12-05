package com.calclab.hablar.login.client;

import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.session.SessionStates;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.suco.client.Suco;

public class HablarLogin {

    private static LoginMessages loginMessages;

    public static LoginMessages i18n() {
	return loginMessages;
    }

    public static void setMessages(final LoginMessages messages) {
	loginMessages = messages;
    }

    private static void setState(final Page<?> login, final String sessionState) {
	if (SessionStates.disconnected.equals(sessionState)) {
	    login.requestVisibility(Visibility.focused);
	}
    }

    // FIXME: move to gin
    @SuppressWarnings("deprecation")
    public HablarLogin(final Hablar hablar, final LoginConfig config) {
	final XmppSession session = Suco.get(XmppSession.class);
	final LoginPage login = new LoginPage(session, hablar.getEventBus(), new LoginWidget());
	hablar.addPage(login);

	session.addSessionStateChangedHandler(true, new StateChangedHandler() {
	    @Override
	    public void onStateChanged(final StateChangedEvent event) {
		setState(login, event.getState());
	    }
	});

	login.getDisplay().getUser().setText(config.userName);
	login.getDisplay().getPassword().setText(config.password);
    }

}
