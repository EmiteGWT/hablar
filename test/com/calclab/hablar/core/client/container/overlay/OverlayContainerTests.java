package com.calclab.hablar.core.client.container.overlay;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.mock.HablarMocks;
import com.calclab.hablar.testing.EventBusTester;
import com.google.gwt.user.client.ui.Widget;

public class OverlayContainerTests {

    private OverlayLayout layout;
    private OverlayContainer container;
    private EventBusTester eventBus;

    @Before
    public void setup() {
	eventBus = new EventBusTester();
	layout = mock(OverlayLayout.class);
	container = new OverlayContainer(eventBus, layout);
    }

    @Test
    public void shouldAddPage() {
	Page<Display> page = HablarMocks.getPage(eventBus);
	assertTrue(container.add(page));
    }

    @Test
    public void shouldHideAtCreation() {
	verify(layout).setVisible(false);
    }

    @Test
    public void shouldHideWhenToggleVisible() {
	Page<Display> page = HablarMocks.getPage(eventBus);
	when(page.getVisibility()).thenReturn(Visibility.focused);
	container.add(page);
	fireChange(page, Visibility.toggle);
	Widget widget = page.getDisplay().asWidget();
	verify(layout).remove(widget);
    }

    @Test
    public void shouldHideWidget() {
	Page<Display> page = HablarMocks.getPage(eventBus);
	container.add(page);
	fireChange(page, Visibility.focused);
	fireChange(page, Visibility.hidden);
	Widget widget = page.getDisplay().asWidget();
	verify(layout).remove(widget);
    }

    @Test
    public void shouldShowFocusedPages() {
	Page<Display> page = HablarMocks.getPage(eventBus);
	when(page.getVisibility()).thenReturn(Visibility.focused);
	container.add(page);
	Widget widget = page.getDisplay().asWidget();
	verify(layout).add(widget);
    }

    @Test
    public void shouldShowWidget() {
	Page<Display> page = HablarMocks.getPage(eventBus);
	container.add(page);
	eventBus.fireEvent(new VisibilityChangeRequestEvent(page, Visibility.focused));
	Widget widget = page.getDisplay().asWidget();
	verify(layout).add(widget);
    }

    private void fireChange(Page<Display> page, Visibility newVisibility) {
	eventBus.fireEvent(new VisibilityChangeRequestEvent(page, newVisibility));
    }
}
