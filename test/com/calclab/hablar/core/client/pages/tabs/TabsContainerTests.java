package com.calclab.hablar.core.client.pages.tabs;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.container.main.MainLayout;
import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.core.mock.HablarMocks;
import com.calclab.hablar.testing.EventBusTester;
import com.calclab.hablar.testing.display.DisplayMocker;
import com.google.gwt.user.client.ui.Widget;

public class TabsContainerTests {

    private TabsContainer container;
    private Page<Display> page;
    private MainLayout layout;
    private EventBusTester eventBus;

    @Before
    public void setup() {
	HablarMocks.disarm();
	eventBus = new EventBusTester();
	layout = mock(MainLayout.class);
	HeaderDisplay headerDisplay = DisplayMocker.mock(HeaderDisplay.class);
	when(layout.createHeaderDisplay((Page<?>) anyObject())).thenReturn(headerDisplay);
	container = new TabsContainer(eventBus, layout);
	page = HablarMocks.getPage(eventBus);
    }

    @Test
    public void shouldHideCurrentWhenFocus() {
	Page<Display> current = HablarMocks.getPage(eventBus);
	setVisibility(Visibility.focused, current);
	container.add(current);

	setVisibility(Visibility.notFocused, page);
	container.add(page);

	fireChange(page, Visibility.focused);
	verify(current).setVisibility(Visibility.notFocused);
	verify(page).setVisibility(Visibility.focused);
    }

    @Test
    public void shouldNotAddHiddenPages() {
	setVisibility(Visibility.hidden, page);
	container.add(page);
	verify(layout, times(0)).add((Widget) anyObject(), (Widget) anyObject());
    }

    @Test
    public void shouldSetFocusedFirstPage() {
	setVisibility(Visibility.notFocused, page);
	container.add(page);
	verify(page).setVisibility(Visibility.focused);
    }

    private void fireChange(Page<Display> page, Visibility newVisibility) {
	eventBus.fireEvent(new VisibilityChangeRequestEvent(page, newVisibility));
    }

    private void setVisibility(Visibility notfocused, Page<Display> page) {
	when(page.getVisibility()).thenReturn(notfocused);
    }

}
