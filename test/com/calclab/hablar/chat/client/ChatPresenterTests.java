package com.calclab.hablar.chat.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.calclab.emite.im.client.chat.Chat;
import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.testing.HablarTester;

public class ChatPresenterTests {

    private ChatDisplay display;
    private ChatPresenter preseter;
    private HablarTester tester;

    @Before
    public void setup() {
	tester = new HablarTester();
	display = tester.newDisplay(ChatDisplay.class);
	final Chat chat = Mockito.mock(Chat.class);
	preseter = new ChatPresenter("test", "id", tester.eventBus, chat, display);
    }

    @Test
    public void shouldFocusTextAreaWhenPageIsFocused() {
	preseter.setVisibility(Visibility.focused);
	verify(display).setTextBoxFocus(true);
    }

    @Test
    public void shouldNotFocusTextAreaWhenBrowserIsNotFocused() {
	tester.setBrowserFocused(false);
	preseter.setVisibility(Visibility.focused);
	verify(display, times(0)).setTextBoxFocus(true);
    }

    @Test
    public void shouldSetVisibilityToFocusWhenFocusOnTextArea() {
	preseter.setVisibility(Visibility.notFocused);
	display.getTextBoxFocus().fireEvent(null);
	assertEquals(Visibility.focused, preseter.getVisibility());
    }

}
