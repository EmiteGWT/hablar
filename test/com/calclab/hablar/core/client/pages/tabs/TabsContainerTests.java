package com.calclab.hablar.core.client.pages.tabs;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.testing.HablarTester;
import com.calclab.hablar.testing.display.DisplayMocker;
import com.google.gwt.user.client.ui.Widget;

public class TabsContainerTests {

    private TabsContainer container;
    private TabsLayout tabsLayout;
    private HablarTester tester;

    @Before
    public void setup() {
	tester = new HablarTester();
	tabsLayout = mock(TabsLayout.class);
	final TabsMenuPresenter presenter = mock(TabsMenuPresenter.class);
	when(tabsLayout.getTabsMenuPresenter()).thenReturn(presenter);
	final HeaderDisplay headerDisplay = DisplayMocker.mock(HeaderDisplay.class);
	when(tabsLayout.createHeaderDisplay((Page<?>) anyObject())).thenReturn(headerDisplay);
	container = new TabsContainer(tester.eventBus, tabsLayout);
    }

    @Test
    public void shouldHideCurrentWhenFocus() {
	final Page<?> current = tester.newPage(Visibility.focused);
	container.add(current);

	final Page<?> page = tester.newPage(Visibility.notFocused);
	container.add(page);

	tester.fire(new VisibilityChangeRequestEvent(page, Visibility.focused));
	verify(current).setVisibility(Visibility.notFocused);
	verify(page).setVisibility(Visibility.focused);
    }

    @Test
    public void shouldNotAddHiddenPages() {
	final Page<?> page = tester.newPage(Visibility.hidden);
	container.add(page);
	verify(tabsLayout, times(0)).add((Widget) anyObject(), (Widget) anyObject());
    }

    @Test
    public void shouldSetFocusedFirstPage() {
	final Page<?> page = tester.newPage(Visibility.notFocused);
	container.add(page);
	verify(page).setVisibility(Visibility.focused);
    }

    @Test
    public void shouldToggleUnfocusedPages() {
	final Page<?> focused = tester.newPage(Visibility.focused);
	final Page<?> notFocused = tester.newPage(Visibility.notFocused);
	container.add(focused);
	container.add(notFocused);
	tester.fire(new VisibilityChangeRequestEvent(notFocused, Visibility.toggle));
	verify(focused).setVisibility(Visibility.notFocused);
	verify(notFocused).setVisibility(Visibility.focused);
    }

}
