package com.calclab.hablar.basic.client.presence;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.chat.client.EmiteTester;
import com.calclab.hablar.chat.client.PresenceManagerTester;

public class PresenceLogicTest {

    private EmiteTester tester;
    private PresenceView view;
    private PresenceLogic logic;
    private PresenceManagerTester presenceManager;

    @Before
    public void setup() {
	tester = new EmiteTester();
	view = mock(PresenceView.class);
	logic = new PresenceLogic(view);
	presenceManager = tester.presenceManager;
    }

    @Test
    public void shouldChangeStatusIfSessionReady() {
	tester.setSessionReady("test1@localhost");
	logic.whenChangeStatusMessage();
	verify(view, times(1)).setStatusBoxVisible(true);

    }

    @Test
    public void shouldChangeStatusMessage() {
	logic.onStatusMessageChanged("epa");
	verify(view, times(2)).setStatusBoxVisible(false);
	verify(view, times(2)).setStatusMessageVisible(true);
	assertEquals("epa", presenceManager.getOwnPresence().getStatus());
    }

    @Test
    public void shouldNotChangeStatusUnlessSessionReady() {
	tester.session.logout();
	logic.whenChangeStatusMessage();
	verify(view, never()).setStatusBoxVisible(true);
    }

    @Test
    public void shouldNotToggleWhenLoggedOut() {
	tester.session.logout();
	logic.onShowChange();
	assertEquals(0, presenceManager.getChangePresenceCallCount());
    }

    @Test
    public void shouldShowNameWhenLoggedIn() {
	tester.session.setLoggedIn("me@host");
	tester.session.setReady();
	verify(view).setName("me");
    }

    @Test
    public void shouldShowOnIconWhenLoggedIn() {
	tester.session.setLoggedIn("me@host");
	tester.session.setReady();
	verify(view).setIcon(HablarIcons.IconType.buddyOn);
    }

    @Test
    public void shouldShowStatusMessageWhenPresenceChanged() {
	presenceManager.getOwnPresence().setStatus("the status");
	presenceManager.setOwnPresence(presenceManager.getOwnPresence());
	verify(view).setStatusMessage("the status");

    }

    @Test
    public void shouldShowTextBox() {
	tester.setSessionReady("test1@host");
	logic.whenChangeStatusMessage();
	verify(view).setStatusMessageVisible(false);
	verify(view).setStatusBoxVisible(true);
    }

    @Test
    public void shouldTogglePresenceBetweenDndAndChat() {
	tester.session.setLoggedIn("me@host");
	tester.session.setReady();
	presenceManager.getOwnPresence().setShow(Show.chat);
	logic.onShowChange();
	assertEquals(Show.dnd, presenceManager.getOwnPresence().getShow());
	logic.onShowChange();
	assertEquals(Show.chat, presenceManager.getOwnPresence().getShow());
    }
}
