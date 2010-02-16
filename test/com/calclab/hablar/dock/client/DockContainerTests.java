package com.calclab.hablar.dock.client;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.core.mock.HablarMocks;
import com.calclab.hablar.dock.client.DockConfig.Position;
import com.calclab.hablar.testing.EventBusTester;
import com.calclab.hablar.testing.display.DisplayMocker;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.LayoutPanel;

public class DockContainerTests {
    private DockConfig config;
    private DockContainer container;
    private EventBusTester eventBus;

    @Before
    public void setup() {
	GWTMockUtilities.disarm();
	eventBus = new EventBusTester();
	config = new DockConfig();
	config.set(Position.top, "top", 24);
	config.set(Position.left, "left", 100);
	final DockLayout layout = mock(DockLayout.class);
	final LayoutPanel panel = HablarMocks.mock(LayoutPanel.class);
	when(layout.addNewPanel(anyString())).thenReturn(panel);
	final HeaderDisplay header = DisplayMocker.mock(HeaderDisplay.class);
	when(layout.createHeaderWidget((Page<?>) anyObject(), (Position) anyObject())).thenReturn(header);
	container = new DockContainer(eventBus, config, layout);

    }

    @Test
    public void shouldAddTop() {
	final Page<Display> page = HablarMocks.getPage(eventBus);
	when(page.getType()).thenReturn("top");
	container.add(page);
	verify(page).setVisibility(Visibility.notFocused);
	setVisibility(page, Visibility.notFocused);
	eventBus.fireEvent(new VisibilityChangeRequestEvent(page, Visibility.toggle));
	verify(page).setVisibility(Visibility.focused);
	setVisibility(page, Visibility.focused);
	eventBus.fireEvent(new VisibilityChangeRequestEvent(page, Visibility.toggle));
	verify(page, times(1)).setVisibility(Visibility.notFocused);
    }

    private void setVisibility(final Page<Display> page, final Visibility visibility) {
	when(page.getVisibility()).thenReturn(visibility);
    }

}
