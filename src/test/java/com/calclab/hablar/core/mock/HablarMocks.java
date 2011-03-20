package com.calclab.hablar.core.mock;

import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PageState;
import com.calclab.hablar.testing.display.DisplayMocker;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.Widget;

public class HablarMocks {

    public static void disarm() {
	GWTMockUtilities.disarm();
    }

    public static Display getDisplay() {
	Display display = DisplayMocker.mock(Display.class);
	Widget widget = getWidget();
	when(display.asWidget()).thenReturn(widget);
	return display;
    }

    @SuppressWarnings("unchecked")
    public static Page<Display> getPage(HablarEventBus eventBus) {
	Page<Display> page = mock(Page.class);
	Display display = getDisplay();
	when(page.getDisplay()).thenReturn(display);
	PageState state = new PageState(eventBus, page);
	when(page.getState()).thenReturn(state);
	return page;
    }

    public static Widget getWidget() {
	Widget widget = mock(Widget.class);
	return widget;
    }

    public static <T> T mock(Class<T> widgetType) {
	return Mockito.mock(widgetType);
    }

}
