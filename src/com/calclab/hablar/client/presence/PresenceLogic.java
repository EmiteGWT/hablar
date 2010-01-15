package com.calclab.hablar.client.presence;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class PresenceLogic {
    private final PresenceView view;
    private Show show;
    private final PresenceManager manager;
    private final Session session;

    public PresenceLogic(final PresenceView view) {
	this.view = view;
	session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(Session session) {
		setState(session);
	    }
	});

	manager = Suco.get(PresenceManager.class);
	manager.onOwnPresenceChanged(new Listener<Presence>() {
	    @Override
	    public void onEvent(Presence presence) {
		setShow(manager.getOwnPresence().getShow());
		setStatusMessage(presence);
	    }

	});
	setState(session);
	view.setStatusBoxVisible(false);
	view.setStatusMessageVisible(true);

    }

    public void onChangeStatusMessage() {
	view.setStatusMessageVisible(false);
	view.setStatusBoxVisible(true);
    }

    public void onShowChange() {
	if (session.getState() == State.ready) {
	    Show nextShow = this.show == Show.dnd ? Show.chat : Show.dnd;
	    Presence presence = manager.getOwnPresence();
	    presence.setShow(nextShow);
	    manager.changeOwnPresence(presence);
	}
    }

    public void onStatusMessageChanged(String statusMessage) {
	view.setStatusMessageVisible(true);
	view.setStatusBoxVisible(false);
	Presence presence = manager.getOwnPresence();
	presence.setStatus(statusMessage);
	manager.changeOwnPresence(presence);
	setStatusMessage(presence);
    }

    private void setShow(Show show) {
	if (show == Show.notSpecified || show == Show.chat) {
	    view.setIcon(IconType.buddyOn);
	} else if (show == Show.dnd) {
	    view.setIcon(IconType.buddyDnd);
	} else if (show == Show.xa) {
	    view.setIcon(IconType.buddyWait);
	}
	this.show = show;
    }

    private void setState(Session session) {
	State state = session.getState();
	if (state == State.disconnected) {
	    view.setName("");
	    view.setIcon(IconType.buddyOff);
	} else if (state == State.ready) {
	    view.setName(session.getCurrentUser().getNode());
	    view.setIcon(IconType.buddyOn);
	}

    }

    private void setStatusMessage(Presence presence) {
	String status = presence.getStatus();
	if (status != null && status.length() > 0)
	    view.setStatusMessage(status);
	else
	    view.setStatusMessage("Click here to set status");
    }
}
