package com.calclab.hablar.client.ui.state;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class StatePanelLogic {
    private final StatePanel panel;
    private Show show;
    private final PresenceManager manager;

    public StatePanelLogic(StatePanel panel) {
	this.panel = panel;
	final Session session = Suco.get(Session.class);
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
	    }
	});
	setState(session);
    }

    public void onShowChange() {
	Show nextShow = this.show == Show.dnd ? Show.chat : Show.dnd;
	Presence presence = manager.getOwnPresence();
	presence.setShow(nextShow);
	manager.setOwnPresence(presence);
	// setShow(nextShow);
    }

    private void setShow(Show show) {
	if (show == Show.notSpecified || show == Show.chat) {
	    panel.setIconClass(panel.style.iconOn());
	} else if (show == Show.dnd) {
	    panel.setIconClass(panel.style.iconDnd());
	} else if (show == Show.xa) {
	    panel.setIconClass(panel.style.iconWait());
	}
	this.show = show;
    }

    private void setState(Session session) {
	State state = session.getState();
	if (state == State.disconnected) {
	    panel.setName(null);
	    panel.setIconClass(panel.style.iconOff());
	} else if (state == State.ready) {
	    panel.setName(session.getCurrentUser().getNode());
	    panel.setIconClass(panel.style.iconOn());
	}

    }
}
