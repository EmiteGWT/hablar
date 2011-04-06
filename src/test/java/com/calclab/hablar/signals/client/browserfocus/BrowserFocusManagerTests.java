package com.calclab.hablar.signals.client.browserfocus;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.calclab.emite.im.client.chat.Chat;
import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.browser.BrowserFocusHandlerStub;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.signals.client.unattended.UnattendedPagesManager;
import com.calclab.hablar.testing.EventBusTester;
import com.calclab.hablar.testing.HablarTester;

public class BrowserFocusManagerTests {

    private BrowserFocusHandlerStub handler;
    private ChatDisplay display;
    private EventBusTester eventBus;
    private Page<?> page;

    @Before
    public void setup() {
	final HablarTester tester = new HablarTester();
	eventBus = tester.eventBus;
	handler = new BrowserFocusHandlerStub();
	final UnattendedPagesManager manager = new UnattendedPagesManager(eventBus, handler);
	new BrowserFocusManager(eventBus, manager, handler);
	display = tester.newDisplay(ChatDisplay.class);
	page = new ChatPresenter(PairChatPresenter.TYPE, "id", eventBus, Mockito.mock(Chat.class), display);
	eventBus.fireEvent(new VisibilityChangedEvent(page, Visibility.focused));
    }

    @Test
    public void shouldNotrestoreFocusFromVisibleIfVisibleUnattended() {
	handler.setFocus(false);
	eventBus.fireEvent(new UserMessageEvent(page, "message", PairChatPresenter.CHAT_MESSAGE));
	handler.setFocus(true);
	verify(display, times(0)).setTextBoxFocus(true);
    }

    @Test
    public void shouldRemoveFocusFromVisiblePageWhenBrowserLoosesTheFocus() {
	handler.setFocus(false);
	verify(display).setTextBoxFocus(false);
    }

    @Test
    public void shouldRestoreFocusFromVisibleWhenBrowserGainsFocusAndPageIsNotUnattended() {
	handler.setFocus(false);
	handler.setFocus(true);
	verify(display).setTextBoxFocus(true);
    }
}
