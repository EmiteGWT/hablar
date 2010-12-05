package com.calclab.hablar.login.client;

import static com.calclab.hablar.login.client.HablarLogin.i18n;

import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.session.SessionStates;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class LoginPage extends PagePresenter<LoginDisplay> {
    public static final String LOGIN_MESSAGE = "LoginMessage";
    private static int index = 0;
    private final XmppSession session;

    public LoginPage(final XmppSession session, final HablarEventBus eventBus, final LoginDisplay display) {
	super("Login", "" + ++index, eventBus, display);
	this.session = session;
	getState().setPageIcon(Icons.NOT_CONNECTED);

	display.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		if (SessionStates.disconnected.equals(session.getSessionState())) {
		    login();
		} else {
		    logout();
		}
	    }
	});

	session.addSessionStateChangedHandler(true, new StateChangedHandler() {
	    @Override
	    public void onStateChanged(final StateChangedEvent event) {
		setState(event.getState());
	    }
	});

    }

    private void fireUserMessage(final String state) {
	final String message = i18n().sessionState(state);
	eventBus.fireEvent(new UserMessageEvent(this, message, LOGIN_MESSAGE));
    }

    protected void login() {
	final String user = display.getUser().getText();
	final String password = display.getPassword().getText();
	session.login(XmppURI.uri(user), password);
    }

    protected void logout() {
	session.logout();
    }

    private void setState(final String state) {
	String actionText, pageTitle;
	String icon;
	boolean actionEnabled;
	if (SessionStates.isReady(state)) {
	    actionText = i18n().logout();
	    actionEnabled = true;
	    final String userName = session.getCurrentUserURI().getNode();
	    pageTitle = i18n().connectedAs(userName);
	    icon = Icons.CONNECTED;
	} else if (SessionStates.isDisconnected(state)) {
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
}
