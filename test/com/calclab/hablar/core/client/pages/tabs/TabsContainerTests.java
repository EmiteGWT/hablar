package com.calclab.hablar.core.client.pages.tabs;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.container.main.MainLayout;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.testing.HablarTester;
import com.calclab.hablar.testing.display.DisplayMocker;
import com.google.gwt.user.client.ui.Widget;

public class TabsContainerTests {

    private TabsContainer container;
    private MainLayout layout;
    private HablarTester tester;

    @Before
    public void setup() {
	tester = new HablarTester();
	layout = mock(MainLayout.class);
	HeaderDisplay headerDisplay = DisplayMocker.mock(HeaderDisplay.class);
	when(layout.createHeaderDisplay((Page<?>) anyObject())).thenReturn(headerDisplay);
	container = new TabsContainer(tester.eventBus, layout);
    }

    @Test
    public void shouldHideCurrentWhenFocus() {
	Page<?> current = tester.newPage(Visibility.focused);
	container.add(current);

	Page<?> page = tester.newPage(Visibility.notFocused);
	container.add(page);

	tester.fire(new VisibilityChangeRequestEvent(page, Visibility.focused));
	verify(current).setVisibility(Visibility.notFocused);
	verify(page).setVisibility(Visibility.focused);
    }

    @Test
    public void shouldNotAddHiddenPages() {
	Page<?> page = tester.newPage(Visibility.hidden);
	container.add(page);
	verify(layout, times(0)).add((Widget) anyObject(), (Widget) anyObject());
    }

    @Test
    public void shouldSetFocusedFirstPage() {
	Page<?> page = tester.newPage(Visibility.notFocused);
	container.add(page);
	verify(page).setVisibility(Visibility.focused);
    }

    @Test
    public void shouldToggleUnfocusedPages() {
	Page<?> focused = tester.newPage(Visibility.focused);
	Page<?> notFocused = tester.newPage(Visibility.notFocused);
	container.add(focused);
	container.add(notFocused);
	tester.fire(new VisibilityChangeRequestEvent(notFocused, Visibility.toggle));
	verify(focused).setVisibility(Visibility.notFocused);
	verify(notFocused).setVisibility(Visibility.focused);
    }

}
