package com.calclab.hablar.testing;

import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.mock.HablarMocks;
import com.calclab.hablar.testing.display.DisplayMocker;
import com.google.gwt.event.shared.GwtEvent;

public class HablarTester {

    public EventBusTester eventBus;

    public HablarTester() {
	HablarIcons.setStyles(new HablarIcons());
	HablarMocks.disarm();
	this.eventBus = new EventBusTester();
    }

    public void fire(GwtEvent<?> event) {
	eventBus.fireEvent(event);
    }

    public EventBusTester getEventBus() {
	return eventBus;
    }

    public <T> T mock(Class<T> classToMock) {
	return Mockito.mock(classToMock);
    }

    public <T extends Display> T newDisplay(Class<T> displayClass) {
	return DisplayMocker.mock(displayClass);
    }

    public Page<?> newPage(Visibility visibility) {
	Page<Display> mock = HablarMocks.getPage(eventBus);
	when(mock.getVisibility()).thenReturn(visibility);
	return mock;
    }
}
