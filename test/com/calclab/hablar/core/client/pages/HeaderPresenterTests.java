package com.calclab.hablar.core.client.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.PageState;
import com.calclab.hablar.core.client.ui.prompt.ConfirmEvent;
import com.calclab.hablar.testing.EventBusTester;
import com.calclab.hablar.testing.HablarTester;
import com.google.gwt.event.dom.client.ClickEvent;

public class HeaderPresenterTests {

    private PageState state;
    private HeaderDisplay display;
    private Page<?> page;
    private EventBusTester eventBus;

    @Before
    public void setup() {
	HablarTester tester = new HablarTester();
	page = mock(Page.class);
	eventBus = tester.eventBus;
	state = new PageState(eventBus, page);
	when(page.getState()).thenReturn(state);
	display = tester.newDisplay(HeaderDisplay.class);
	new HeaderPresenter(eventBus, page, display);
    }

    @Test
    public void shouldCloseIfConfirmed() {
	state.setCloseConfirmationMessage("confirm");
	state.setCloseConfirmationTitle("confirmTitle");
	display.getClose().fireEvent(mock(ClickEvent.class));
	assertEquals(ConfirmEvent.class, eventBus.getLastEventClass());
	ConfirmEvent confirmEvent = (ConfirmEvent) eventBus.getLastEvent();
	assertEquals("confirm", confirmEvent.message);
	assertEquals("confirmTitle", confirmEvent.title);
	confirmEvent.handler.accept();
	verify(page).requestVisibility(Visibility.hidden);
    }

    @Test
    public void shouldCloseIfNoConfirmation() {
	state.setCloseConfirmationMessage(null);
	display.getClose().fireEvent(mock(ClickEvent.class));
	verify(page).requestVisibility(Visibility.hidden);
    }

    @Test
    public void shouldShowConfirmationIfAny() {
	state.setCloseConfirmationMessage(null);
	display.getClose().fireEvent(mock(ClickEvent.class));
	assertFalse(eventBus.receivedEventOfClass(ConfirmEvent.class));
	state.setCloseConfirmationMessage("confirmation");
	display.getClose().fireEvent(mock(ClickEvent.class));
	assertEquals(ConfirmEvent.class, eventBus.getLastEventClass());
    }
}
