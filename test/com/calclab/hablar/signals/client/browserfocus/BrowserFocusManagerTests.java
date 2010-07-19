package com.calclab.hablar.signals.client.browserfocus;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.signals.client.BrowserFocusHandlerStub;
import com.calclab.hablar.testing.HablarTester;

public class BrowserFocusManagerTests {

    private HablarTester tester;
    private BrowserFocusHandlerStub handler;

    @Before
    public void setup() {
	tester = new HablarTester();
	handler = new BrowserFocusHandlerStub();
	new BrowserFocusManager(tester.eventBus, handler);
    }

    @Test
    public void shouldRemoveFocusFromVisiblePageWhenBrowserLoosesTheFocus() {
	ChatDisplay display = tester.newDisplay(ChatDisplay.class);
	Page<?> page = new ChatPresenter(PairChatPresenter.TYPE, "id", tester.eventBus, display);
	tester.eventBus.fireEvent(new VisibilityChangedEvent(page, Visibility.focused));
	handler.setFocus(false);
	verify(display).setTextBoxFocus(false);
    }
}
