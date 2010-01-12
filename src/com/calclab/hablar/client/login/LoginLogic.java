package com.calclab.hablar.client.login;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener0;

public class LoginLogic {
    private final Session session;
    private final LoginPage page;

    public LoginLogic(final LoginPage widget) {
	this.page = widget;
	this.session = Suco.get(Session.class);

	String userName = PageAssist.getMeta("hablar.user");
	widget.setUserName(userName);

	session.onStateChanged(new Listener0() {
	    @Override
	    public void onEvent() {
		setState(session.getState());
	    }
	});
	setState(session.getState());
    }

    public void onAction() {
	State state = session.getState();
	if (state == State.disconnected) {
	    XmppURI uri = getUri();
	    if (uri != null) {
		session.login(uri, page.getPassword());
	    }
	} else if (state == State.ready) {
	    session.logout();
	}
    }

    // FIXME: XmppURI class needs a look
    private XmppURI getUri() {
	try {
	    return XmppURI.uri(page.getUserName());
	} catch (RuntimeException e) {
	    return null;
	}
    }

    private void setState(State state) {
	if (state == State.ready) {
	    page.setActionText("Logout");
	    page.setActionEnabled(true);
	    String userName = session.getCurrentUser().getNode();
	    setStatus("Connected as " + userName, page.headerStyle.loggedInIcon());
	} else if (state == State.disconnected) {
	    page.setActionText("Login");
	    page.setActionEnabled(true);
	    setStatus("Disconnected", page.headerStyle.loggedOutIcon());
	} else {
	    page.setActionText("Wait...");
	    page.setActionEnabled(false);
	}
	page.addMessage("Session state: " + state);
    }

    private void setStatus(String status, String iconStyle) {
	page.setStatus(status);
	page.setHeaderTitle(status);
	page.setHeaderIconClass(iconStyle);
    }

}
