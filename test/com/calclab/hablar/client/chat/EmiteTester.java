package com.calclab.hablar.client.chat;

import com.calclab.emite.core.client.EmiteCoreModule;
import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.im.client.InstantMessagingModule;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xtesting.SessionTester;
import com.calclab.hablar.client.roster.RosterTester;
import com.calclab.hablar.client.search.SearchManagerTester;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.SucoCoreModule;
import com.calclab.suco.client.ioc.Container;
import com.calclab.suco.client.ioc.HashMapContainer;
import com.calclab.suco.client.ioc.Provider;
import com.calclab.suco.client.ioc.decorator.NoDecoration;

public class EmiteTester {
    public final SessionTester session;

    public final ChatManagerTester chatManager;
    public final RosterTester roster;
    public final SearchManagerTester searchManager;
    public final PresenceManagerTester presenceManager;

    public EmiteTester() {
	// FIXME: Add setContainer to Suco (switch context)
	HashMapContainer container = (HashMapContainer) Suco.getComponents();
	container.clear();
	new SucoCoreModule().onInstall(container);
	Suco.install(new EmiteCoreModule(), new InstantMessagingModule());

	session = new SessionTester();
	install(container, Session.class, session);

	chatManager = new ChatManagerTester(session);
	install(container, ChatManager.class, chatManager);

	roster = new RosterTester();
	install(container, Roster.class, roster);

	searchManager = new SearchManagerTester();
	install(container, SearchManager.class, searchManager);

	presenceManager = new PresenceManagerTester();
	install(container, PresenceManager.class, presenceManager);
    }

    private <T> T install(Container container, Class<T> clazz, final T instance) {
	container.removeProvider(clazz);
	container.registerProvider(NoDecoration.instance, clazz, new Provider<T>() {
	    @Override
	    public T get() {
		return instance;
	    }
	});
	return instance;
    }

}
