package com.calclab.hablar.chat.client;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.testing.HablarTester;
import static org.mockito.Mockito.*;

public class ChatPresenterTests {

    private ChatDisplay display;
    private ChatPresenter preseter;
    private HablarTester tester;

    @Before
    public void setup() {
	tester = new HablarTester();
	display = tester.newDisplay(ChatDisplay.class);
	preseter = new ChatPresenter("test", "id", tester.eventBus, display );
    }
    
    @Test
    public void shouldSetVisibilityToFocusWhenFocusOnTextArea() {
	preseter.setVisibility(Visibility.notFocused);
	display.getTextBoxFocus().fireEvent(null);
	assertEquals(Visibility.focused, preseter.getVisibility());
    }
    
    @Test
    public void shouldFocusTextAreaWhenPageIsFocused() {
	preseter.setVisibility(Visibility.focused);
	verify(display).focusInput();
    }
    
    @Test
    public void shouldNotFocusTextAreaWhenBrowserIsNotFocused() {
	tester.setBrowserFocused(false);
	preseter.setVisibility(Visibility.focused);
	verify(display, times(0)).focusInput();
    }

}
