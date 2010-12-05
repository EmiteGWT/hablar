package com.calclab.hablar.testing;

import org.mockito.Mockito;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.XmppRosterLogic;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xtesting.XmppSessionTester;
import com.calclab.hablar.chat.client.ChatManagerTester;
import com.calclab.hablar.chat.client.PresenceManagerTester;
import com.calclab.hablar.core.client.mvp.DefaultEventBus;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.search.client.SearchManagerTester;
import com.calclab.hablar.testing.display.DisplayStubFactory;
import com.calclab.hablar.testing.display.ReturnsSingletonMocks;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.SucoCoreModule;
import com.calclab.suco.client.ioc.Container;
import com.calclab.suco.client.ioc.HashMapContainer;
import com.calclab.suco.client.ioc.Provider;
import com.calclab.suco.client.ioc.decorator.NoDecoration;

public class EmiteTester {
    private static DisplayStubFactory factory = DisplayStubFactory.instance;

    public final XmppSessionTester session;

    public final ChatManagerTester chatManager;
    public final RosterTester roster;
    public final SearchManagerTester searchManager;
    public final PresenceManagerTester presenceManager;

    public DefaultEventBus eventBus;

    public XmppRosterLogic xmppRoster;

    public EmiteTester() {
	final HashMapContainer container = (HashMapContainer) Suco.getComponents();
	container.clear();
	new SucoCoreModule().onInstall(container);
	Suco.install(new EmiteCoreModule(), new InstantMessagingModule());

	eventBus = new DefaultEventBus();
	install(container, HablarEventBus.class, eventBus);

	session = new XmppSessionTester();
	install(container, XmppSession.class, session);

	chatManager = new ChatManagerTester(session);
	install(container, ChatManager.class, chatManager);

	xmppRoster = new XmppRosterLogic(session);
	roster = new RosterTester(xmppRoster);
	install(container, Roster.class, roster);

	searchManager = new SearchManagerTester();
	install(container, SearchManager.class, searchManager);

	presenceManager = new PresenceManagerTester(session);
	install(container, PresenceManager.class, presenceManager);
    }

    private <T> T install(final Container container, final Class<T> clazz, final T instance) {
	container.removeProvider(clazz);
	container.registerProvider(NoDecoration.instance, clazz, new Provider<T>() {
	    @Override
	    public T get() {
		return instance;
	    }
	});
	return instance;
    }

    public <T> T mockDisplay(final Class<T> classToMock) {
	final T mock = Mockito.mock(classToMock, new ReturnsSingletonMocks(factory));
	return mock;
    }

    public void setSessionReady(final String uri) {
	session.setLoggedIn(uri);
	session.setReady();
    }

}
