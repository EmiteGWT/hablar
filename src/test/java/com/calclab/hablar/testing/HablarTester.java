package com.calclab.hablar.testing;

import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.calclab.hablar.core.client.browser.BrowserFocusHandler;
import com.calclab.hablar.core.client.browser.BrowserFocusHandlerStub;
import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.core.mock.HablarMocks;
import com.calclab.hablar.rooms.client.HablarRooms;
import com.calclab.hablar.rooms.client.RoomsMessages;
import com.calclab.hablar.roster.client.HablarRoster;
import com.calclab.hablar.roster.client.RosterBasicActions;
import com.calclab.hablar.roster.client.RosterMessages;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.selenium.tools.I18nHelper;
import com.calclab.hablar.testing.display.DisplayMocker;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.i18n.client.Messages;

public class HablarTester {

    public EventBusTester eventBus;

    public HablarTester() {
	HablarMocks.disarm();
	BrowserFocusHandler.setInstance(new BrowserFocusHandlerStub());
	eventBus = new EventBusTester();

	
	final RosterMessages messages = newMessages(RosterMessages.class);
	HablarRoster.setMessages(messages);
	RosterBasicActions.setMessages(messages);
	RosterGroupPresenter.setMessages(messages);
	HablarRooms.setMessages(newMessages(RoomsMessages.class));
    }

    public void fire(final GwtEvent<?> event) {
	eventBus.fireEvent(event);
    }

    public EventBusTester getEventBus() {
	return eventBus;
    }

    public <T> T mock(final Class<T> classToMock) {
	return Mockito.mock(classToMock);
    }

    public <T extends Display> T newDisplay(final Class<T> displayClass) {
	return DisplayMocker.mock(displayClass);
    }

    @SuppressWarnings("unchecked")
    public <T> Menu<T> newMenu() {
	final MenuDisplay<T> display = newDisplay(MenuDisplay.class);
	return new Menu<T>(display);
    }

    public Page<?> newPage(final Visibility visibility) {
	final Page<Display> mock = HablarMocks.getPage(eventBus);
	when(mock.getVisibility()).thenReturn(visibility);
	return mock;
    }

    private <T extends Messages> T newMessages(final Class<T> classToMock) {
	final Answer<Object> answer = new Answer<Object>() {
	    @Override
	    public Object answer(final InvocationOnMock invocation) throws Throwable {
		return I18nHelper.get(classToMock, invocation.getMethod(), invocation.getArguments());
	    }
	};
	return Mockito.mock(classToMock, answer);
    }

    public void setBrowserFocused(boolean hasFocus) {
	BrowserFocusHandler.getInstance().setFocus(hasFocus);
    }
}
