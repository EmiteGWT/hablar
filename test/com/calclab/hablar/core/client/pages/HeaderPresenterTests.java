package com.calclab.hablar.core.client.pages;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.browser.BrowserStub;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.PageState;
import com.calclab.hablar.testing.HablarTester;
import com.google.gwt.event.dom.client.ClickEvent;

public class HeaderPresenterTests {

    private HeaderPresenter presenter;
    private PageState state;
    private HeaderDisplay display;
    private BrowserStub browser;
    private Page<?> page;

    @Before
    public void setup() {
	HablarTester tester = new HablarTester();
	browser = new BrowserStub();
	page = mock(Page.class);
	state = new PageState(tester.eventBus, page);
	when(page.getState()).thenReturn(state);
	display = tester.newDisplay(HeaderDisplay.class);
	presenter = new HeaderPresenter(page, display);
    }

    @Test
    public void shouldCloseIfConfirmed() {
	state.setCloseConfirmationMessage("confirm");
	browser.setConfirmationValue(true);
	display.getClose().fireEvent(mock(ClickEvent.class));
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
	assertEquals(0, browser.getCalledTimes());
	state.setCloseConfirmationMessage("confirmation");
	display.getClose().fireEvent(mock(ClickEvent.class));
	assertEquals(1, browser.getCalledTimes());
    }
}
