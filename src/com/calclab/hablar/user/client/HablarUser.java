package com.calclab.hablar.user.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.Hablar.Chain;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.user.client.presence.PresencePage;
import com.calclab.hablar.user.client.presence.PresenceWidget;
import com.calclab.hablar.user.client.storedpresence.StoredPresenceManager;

public class HablarUser {

    private static UserMessages userMessages;

    public static UserMessages i18n() {
	return userMessages;
    }

    public static void setMessages(final UserMessages messages) {
	userMessages = messages;
    }

    public HablarUser(final Hablar hablar, final XmppSession session, final PresenceManager presenceManager,
	    final PrivateStorageManager privateStorageManager) {
	final StoredPresenceManager storedPresenceManager = new StoredPresenceManager(privateStorageManager);

	final HablarEventBus eventBus = hablar.getEventBus();
	final UserPage user = new UserPage(session, presenceManager, eventBus, new UserWidget());
	hablar.addPage(user);

	final UserContainer container = new UserContainer(eventBus, user);
	hablar.addContainer(container, Chain.after);

	final PresenceWidget display = new PresenceWidget();

	final PresencePage presence = new PresencePage(presenceManager, storedPresenceManager, eventBus, display);
	hablar.addPage(presence, UserContainer.ROL);
    }

}
