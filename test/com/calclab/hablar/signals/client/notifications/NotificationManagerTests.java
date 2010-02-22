package com.calclab.hablar.signals.client.notifications;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.signals.client.SignalPreferences;
import com.calclab.hablar.testing.EventBusTester;
import com.calclab.hablar.testing.HablarTester;

public class NotificationManagerTests {

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
	new NotificationManager(eventBus, preferences, notifier);
    }

    @Test
    public void shouldShowChatMessagesIfChoosed() {
	preferences.incomingMessages = false;
	fire("message", ChatPresenter.CHAT_MESSAGE);
	verify(notifier, times(0)).show("message");
	preferences.incomingMessages = true;
	fire("message", ChatPresenter.CHAT_MESSAGE);
	verify(notifier, times(1)).show("message");
    }

    @Test
    public void shouldShowRoomMessagesIfChoosed() {
	preferences.incomingMessages = false;
	fire("message", RoomPresenter.ROOM_MESSAGE);
	verify(notifier, times(0)).show("message");
	preferences.incomingMessages = true;
	fire("message", RoomPresenter.ROOM_MESSAGE);
	verify(notifier, times(1)).show("message");
    }

    @Test
    public void shouldShowRosterMessagesIfChoosed() {
	preferences.rosterNotifications = false;
	fire("message", RosterPresenter.ROSTER_MESSAGE);
	verify(notifier, times(0)).show("message");
	preferences.rosterNotifications = true;
	fire("message", RosterPresenter.ROSTER_MESSAGE);
	verify(notifier, times(1)).show("message");
    }

    private void fire(final String message, final String type) {
	eventBus.fireEvent(new UserMessageEvent(page, message, type));
    }
}
