package com.calclab.hablar.core.client.pages.accordion;

import static org.mockito.Matchers.anyObject;
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

public class AccordionContainerTests {

    private HablarTester tester;
    private AccordionContainer container;

    @Before
    public void setup() {
	tester = new HablarTester();
	MainLayout layout = tester.mock(MainLayout.class);
	HeaderDisplay headerDisplay = tester.newDisplay(HeaderDisplay.class);
	when(layout.createHeaderDisplay((Page<?>) anyObject())).thenReturn(headerDisplay);
	container = new AccordionContainer(tester.eventBus, layout);
    }

    @Test
    public void shouldFocusFirstPage() {
	Page<?> firstPage = tester.newPage(Visibility.notFocused);
	container.add(firstPage);
	verify(firstPage).setVisibility(Visibility.focused);
	Page<?> secondPage = tester.newPage(Visibility.notFocused);
	container.add(secondPage);
	verify(secondPage, times(0)).setVisibility(Visibility.focused);
    }

    @Test
    public void shouldToggleFromHidden() {
	Page<?> firstPage = tester.newPage(Visibility.focused);
	Page<?> secondPage = tester.newPage(Visibility.notFocused);
	container.add(firstPage);
	container.add(secondPage);
	tester.fire(new VisibilityChangeRequestEvent(secondPage, Visibility.toggle));
	verify(firstPage).setVisibility(Visibility.notFocused);
	verify(secondPage).setVisibility(Visibility.focused);
    }
}
