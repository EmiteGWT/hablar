package com.calclab.hablar.signals.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.browser.BrowserFocusHandlerStub;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.signals.client.unattended.UnattendedPagesManager;
import com.calclab.hablar.testing.HablarTester;

public class UnattendedPagesManagerTests {

    private UnattendedPagesManager pages;
    private HablarTester tester;
    private BrowserFocusHandlerStub focusManager;

    @Before
    public void setup() {
	tester = new HablarTester();
	focusManager = new BrowserFocusHandlerStub();
	focusManager.setFocus(true);
	pages = new UnattendedPagesManager(tester.eventBus, focusManager);
    }

    @Test
    public void shouldAddFocusedChatIfBrowserIsNotFocused() {
	final Page<?> page = createChatPage(Visibility.focused);
	assertEquals(0, pages.getSize());
	focusManager.setFocus(false);
	tester.fire(new UserMessageEvent(page, "message", PairChatPresenter.CHAT_MESSAGE));
	assertTrue(pages.contains(page));
    }

    @Test
    public void shouldAddChatIfNewMessageAndNotFocused() {
	final Page<?> page = createChatPage(Visibility.notFocused);
	assertEquals(0, pages.getSize());
	tester.fire(new UserMessageEvent(page, "message", PairChatPresenter.CHAT_MESSAGE));
	assertTrue(pages.contains(page));
    }

    /**
     * Issue 287: An unattended chat tab can be closed without the unattended
     * flag being reset
     */
    @Test
    public void shouldRemoveChatIfClosed() {
	final Page<?> page = createChatPage(Visibility.notFocused);
	tester.fire(new UserMessageEvent(page, "message", PairChatPresenter.CHAT_MESSAGE));
	assertTrue(pages.contains(page));
	tester.fire(new VisibilityChangedEvent(page, Visibility.hidden));
	assertFalse(pages.contains(page));
    }

    @Test
    public void shouldRemoveChatIfFocused() {
	final Page<?> page = createChatPage(Visibility.notFocused);
	tester.fire(new UserMessageEvent(page, "message", PairChatPresenter.CHAT_MESSAGE));
	assertTrue(pages.contains(page));
	tester.fire(new VisibilityChangedEvent(page, Visibility.focused));
	assertFalse(pages.contains(page));
    }

    private Page<?> createChatPage(final Visibility visibility) {
	final Page<?> page = tester.newPage(visibility);
	when(page.getType()).thenReturn(PairChatPresenter.TYPE);
	return page;
    }
}
