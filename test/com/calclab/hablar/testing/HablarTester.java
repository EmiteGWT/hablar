package com.calclab.hablar.testing;

import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.core.mock.HablarMocks;
import com.calclab.hablar.testing.display.DisplayMocker;
import com.google.gwt.event.shared.GwtEvent;

public class HablarTester {

    public EventBusTester eventBus;

    public HablarTester() {
	HablarIcons.setStyles(new HablarIcons());
	HablarMocks.disarm();
	eventBus = new EventBusTester();
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
}
