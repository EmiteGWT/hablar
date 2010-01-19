package com.calclab.hablar.basic.client.login;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.icons.HablarIcons;
import com.calclab.hablar.basic.client.ui.icons.HablarIcons.IconType;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;

class LoginLogic {
    private final Session session;
    private final LoginPage page;
    private final Msg i18n;

    public LoginLogic(final LoginPage widget) {
	this.page = widget;
	this.session = Suco.get(Session.class);
	this.i18n = Suco.get(Msg.class);

	final String userName = PageAssist.getMeta("hablar.user");
	widget.setUserName(userName);
	widget.setPassword(PageAssist.getMeta("hablar.password"));

	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		setState(session.getState());
	    }
	});
	setState(session.getState());

	Window.addCloseHandler(new CloseHandler<Window>() {
	    public void onClose(final CloseEvent<Window> arg0) {
		PageAssist.closeSession(session);
	    }
	});
    }

    public void onAction() {
	final State state = session.getState();
	if (state == State.disconnected) {
	    final XmppURI uri = getUri();
	    if (uri != null) {
		session.login(uri, page.getPassword());
	    }
	} else if (state == State.ready) {
	    session.logout();
	}
    }

    private XmppURI getUri() {
	return XmppURI.uri(page.getUserName());
    }

    private void setState(final State state) {
	if (state == State.ready) {
	    page.setActionText(i18n.logout());
	    page.setActionEnabled(true);
	    final String userName = session.getCurrentUser().getNode();
	    setStatus(i18n.connectedAs(userName), HablarIcons.get(IconType.on));
	} else if (state == State.disconnected) {
	    page.setActionText(i18n.login());
	    page.setActionEnabled(true);
	    setStatus(i18n.disconnected(), HablarIcons.get(IconType.off));
	} else {
	    page.setActionText(i18n.waitDots());
	    page.setActionEnabled(false);
	}
	page.addMessage("Session state: " + state);
    }

    private void setStatus(final String status, final String iconStyle) {
	page.setStatusMessage(status);
	page.setHeaderTitle(status);
	page.setHeaderIconClass(iconStyle);
    }

}
