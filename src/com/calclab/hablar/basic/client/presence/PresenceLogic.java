package com.calclab.hablar.basic.client.presence;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.hablar.basic.client.ui.styles.HablarStyles;
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
	    public void onEvent(final Session session) {
		setState(session);
	    }
	});

	manager = Suco.get(PresenceManager.class);
	manager.onOwnPresenceChanged(new Listener<Presence>() {
	    @Override
	    public void onEvent(final Presence presence) {
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
	view.setStatusBoxFocus(true);
    }

    public void onShowChange() {
	if (session.getState() == State.ready) {
	    final Show nextShow = this.show == Show.dnd ? Show.chat : Show.dnd;
	    final Presence presence = manager.getOwnPresence();
	    presence.setShow(nextShow);
	    manager.changeOwnPresence(presence);
	}
    }

    public void onStatusMessageChanged(final String statusMessage) {
	view.setStatusMessageVisible(true);
	view.setStatusBoxVisible(false);
	final Presence presence = manager.getOwnPresence();
	presence.setStatus(statusMessage);
	manager.changeOwnPresence(presence);
	setStatusMessage(presence);
    }

    private void setShow(final Show show) {
	if (show == Show.notSpecified || show == Show.chat) {
	    view.setIcon(HablarStyles.IconType.buddyOn);
	} else if (show == Show.dnd) {
	    view.setIcon(HablarStyles.IconType.buddyDnd);
	} else if (show == Show.xa) {
	    view.setIcon(HablarStyles.IconType.buddyWait);
	}
	this.show = show;
    }

    private void setState(final Session session) {
	final State state = session.getState();
	if (state == State.disconnected) {
	    view.setName("");
	    view.setIcon(HablarStyles.IconType.buddyOff);
	} else if (state == State.ready) {
	    view.setName(session.getCurrentUser().getNode());
	    view.setIcon(HablarStyles.IconType.buddyOn);
	}

    }

    private void setStatusMessage(final Presence presence) {
	final String status = presence.getStatus();
	if (status != null && status.length() > 0) {
	    view.setStatusMessage(status);
	} else {
	    view.setStatusMessage("Click here to set status");
	}
    }
}
