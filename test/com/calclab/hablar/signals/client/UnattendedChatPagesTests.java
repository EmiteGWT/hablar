package com.calclab.hablar.signals.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.UserMessageChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.signals.client.unattended.UnattendedChatPages;
import com.calclab.hablar.testing.HablarTester;

public class UnattendedChatPagesTests {

    private UnattendedChatPages pages;
    private HablarTester tester;

    @Before
    public void setup() {
	tester = new HablarTester();
	pages = new UnattendedChatPages(tester.eventBus);
    }

    @Test
    public void shouldAddChatIfNewMessageAndNotFocused() {
	final Page<?> page = createChatPage(Visibility.notFocused);
	assertEquals(0, pages.getSize());
	tester.fire(new UserMessageChangedEvent(page, "message"));
	assertTrue(pages.contains(page));
    }

    @Test
    public void shouldRemoveChatIfFocused() {
	final Page<?> page = createChatPage(Visibility.notFocused);
	tester.fire(new UserMessageChangedEvent(page, "message"));
	assertTrue(pages.contains(page));
	tester.fire(new VisibilityChangedEvent(page, Visibility.focused));
	assertFalse(pages.contains(page));
    }

    private Page<?> createChatPage(final Visibility visibility) {
	final Page<?> page = tester.newPage(visibility);
	when(page.getType()).thenReturn(ChatPresenter.TYPE);
	return page;
    }
}
