package com.calclab.hablar.login.client;

import static com.calclab.hablar.login.client.HablarLogin.i18n;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class LoginPage extends PagePresenter<LoginDisplay> {
    public static final String LOGIN_MESSAGE = "LoginMessage";
    private static int index = 0;
    private final Session session;

    public LoginPage(final HablarEventBus eventBus, final LoginDisplay display) {
	super("Login", "" + ++index, eventBus, display);
	session = Suco.get(Session.class);
	getState().setPageIcon(Icons.NOT_CONNECTED);

	display.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		if (session.getState() == State.disconnected) {
		    login();
		} else {
		    logout();
		}
	    }
	});

	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		setState(session.getState());
	    }
	});
	setState(session.getState());
    }

    private void fireUserMessage(final State state) {
	final String message = i18n().sessionState(state.toString());
	eventBus.fireEvent(new UserMessageEvent(this, message, LOGIN_MESSAGE));
    }

    private void setState(final State state) {
	String actionText, pageTitle;
	String icon;
	boolean actionEnabled;
	if (state == State.ready) {
	    actionText = i18n().logout();
	    actionEnabled = true;
	    final String userName = session.getCurrentUser().getNode();
	    pageTitle = i18n().connectedAs(userName);
	    icon = Icons.CONNECTED;
	} else if (state == State.disconnected) {
	    actionText = i18n().login();
	    actionEnabled = true;
	    pageTitle = i18n().disconnected();
	    icon = Icons.NOT_CONNECTED;
	} else {
	    pageTitle = actionText = i18n().waitDots();
	    actionEnabled = false;
	    icon = Icons.NOT_CONNECTED;
	}
	display.getActionText().setText(actionText);
	display.setActionEnabled(actionEnabled);
	display.addMessage(i18n().sessionStateMessage(state.toString()));
	getState().setPageTitle(pageTitle);
	getState().setPageTitleTooltip(pageTitle);
	getState().setPageIcon(icon);
	fireUserMessage(state);
    }

    protected void login() {
	final String user = display.getUser().getText();
	final String password = display.getPassword().getText();
	session.login(XmppURI.uri(user), password);
    }

    protected void logout() {
	session.logout();
    }
}
