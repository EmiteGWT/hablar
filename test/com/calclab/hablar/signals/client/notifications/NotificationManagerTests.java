package com.calclab.hablar.signals.client.notifications;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;

import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.signals.client.SignalPreferences;
import com.calclab.hablar.testing.EventBusTester;
import com.calclab.hablar.testing.HablarTester;

public class NotificationManagerTests {

    private final NotificationManager notificationManager;
    private final SignalPreferences preferences;
    private final HablarNotifier notifier;
    private final EventBusTester eventBus;
    private final Page<?> page;

    public NotificationManagerTests() {
	final HablarTester tester = new HablarTester();
	eventBus = tester.getEventBus();
	preferences = new SignalPreferences();
	notifier = mock(HablarNotifier.class);
	page = mock(Page.class);
	notificationManager = new NotificationManager(eventBus, preferences, notifier);
    }

    @Test
    public void shouldShowChatMessagesIfChoosed() {
	preferences.incomingMessages = false;
	fire("message", PairChatPresenter.CHAT_MESSAGE);
	verify(notifier, times(0)).show("message", PairChatPresenter.CHAT_MESSAGE);
	preferences.incomingMessages = true;
	fire("message", PairChatPresenter.CHAT_MESSAGE);
	verify(notifier, times(1)).show("message", PairChatPresenter.CHAT_MESSAGE);
    }

    @Test
    public void shouldShowRoomMessagesIfChoosed() {
	preferences.incomingMessages = false;
	fire("message", RoomPresenter.ROOM_MESSAGE);
	verify(notifier, times(0)).show("message", RoomPresenter.ROOM_MESSAGE);
	preferences.incomingMessages = true;
	fire("message", RoomPresenter.ROOM_MESSAGE);
	verify(notifier, times(1)).show("message", RoomPresenter.ROOM_MESSAGE);
    }

    @Test
    public void shouldShowRosterMessagesIfChoosed() {
	preferences.rosterNotifications = false;
	fire("message", RosterPresenter.ROSTER_MESSAGE);
	verify(notifier, times(0)).show("message", RosterPresenter.ROSTER_MESSAGE);
	preferences.rosterNotifications = true;
	fire("message", RosterPresenter.ROSTER_MESSAGE);
	verify(notifier, times(1)).show("message", RosterPresenter.ROSTER_MESSAGE);
    }

    @Test
    public void testAddAndRetrieveNotifiers() {
	HablarNotifier activeNotifier = mock(HablarNotifier.class);
	HablarNotifier inactiveNotifier = mock(HablarNotifier.class);
	notificationManager.addNotifier(activeNotifier, true);
	notificationManager.addNotifier(inactiveNotifier, false);
	
	List<HablarNotifier> notifiers = notificationManager.getAvailableNotifiers();
	List<HablarNotifier> activeNotifiers = notificationManager.getActiveNotifiers();
	
	assertEquals("Wrong number of notifiers returned", 3, notifiers.size());
	assertTrue("Initial notifier not returned", notifiers.contains(notifier));
	assertTrue("Active notifier not returned", notifiers.contains(activeNotifier));
	assertTrue("Inactive notifier not returned", notifiers.contains(inactiveNotifier));
	
	assertEquals("Wrong number of active notifiers returned", 2, activeNotifiers.size());
	assertTrue("Initial notifier not returned", activeNotifiers.contains(notifier));
	assertTrue("Active notifier not returned", activeNotifiers.contains(activeNotifier));
    }

    @Test
    public void testIsNotifierActive() {
	HablarNotifier activeNotifier = mock(HablarNotifier.class);
	HablarNotifier inactiveNotifier = mock(HablarNotifier.class);
	HablarNotifier nonExistantNotifier = mock(HablarNotifier.class);
	notificationManager.addNotifier(activeNotifier, true);
	notificationManager.addNotifier(inactiveNotifier, false);

	assertTrue("Active notifier not identified", notificationManager.isNotifierActive(activeNotifier));
	assertFalse("Inactive notifier not identified", notificationManager.isNotifierActive(inactiveNotifier));
	assertFalse("Non-existant notifier not identified", notificationManager.isNotifierActive(nonExistantNotifier));
    }
    
    @Test
    public void testSetNotifierActive() {
	notificationManager.setNotifierActive(notifier, false);
	assertFalse("Notifier not deactivated", notificationManager.isNotifierActive(notifier));
	notificationManager.setNotifierActive(notifier, true);
	assertTrue("Notifier not activated", notificationManager.isNotifierActive(notifier));
    }
    
    private void fire(final String message, final String type) {
	eventBus.fireEvent(new UserMessageEvent(page, message, type));
    }
}
