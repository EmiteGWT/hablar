package com.calclab.hablar.user.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class UserPresenter extends PagePresenter<UserDisplay> {

    private static int index = 0;
    public static final String TYPE = "User";
    private final Session session;
    private final PresenceManager manager;

    public UserPresenter(HablarEventBus eventBus, UserDisplay display) {
	super(TYPE, "" + (++index), eventBus, display);
	session = Suco.get(Session.class);
	manager = Suco.get(PresenceManager.class);

	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		setState(session, manager.getOwnPresence());
	    }
	});

	manager.onOwnPresenceChanged(new Listener<Presence>() {
	    @Override
	    public void onEvent(final Presence presence) {
		setShow(presence.getShow());
	    }
	});

	Presence currentPresence = manager.getOwnPresence();
	setState(session, currentPresence);
	model.setPageIcon(HablarIcons.get(IconType.buddyOff));
    }

    private void setShow(final Show show) {
	if (show == Show.notSpecified || show == Show.chat) {
	    model.setPageIcon(HablarIcons.get(HablarIcons.IconType.buddyOn));
	} else if (show == Show.dnd) {
	    model.setPageIcon(HablarIcons.get(HablarIcons.IconType.buddyDnd));
	} else if (show == Show.xa) {
	    model.setPageIcon(HablarIcons.get(HablarIcons.IconType.buddyWait));
	}
    }

    private void setState(final Session session, Presence presence) {
	String userStatus = presence != null ? presence.getStatus() : "";
	userStatus = (userStatus == null || userStatus.isEmpty()) ? "Click here to change status" : userStatus;
	final State s = session.getState();
	if (s == State.disconnected) {
	    model.setPageTitle(userStatus);
	    model.setPageIcon(HablarIcons.get(HablarIcons.IconType.buddyOff));
	} else if (s == State.ready) {
	    model.setPageTitle(session.getCurrentUser().getShortName() + " - " + userStatus);
	    model.setPageIcon(HablarIcons.get(HablarIcons.IconType.buddyOn));
	}
    }

}
