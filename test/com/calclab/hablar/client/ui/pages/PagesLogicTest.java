package com.calclab.hablar.client.ui.pages;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.client.ui.page.Page;
import com.calclab.hablar.client.ui.page.Page.Visibility;

public class PagesLogicTest {
    private PagesPanel view;
    private PagesLogic logic;

    @Before
    public void setup() {
	view = mock(PagesPanel.class);
	logic = new PagesLogic(view);
    }

    @Test
    public void shouldAddClosedPages() {
	shouldAddPage(Visibility.closed);
    }

    @Test
    public void shouldAddOpenPages() {
	shouldAddPage(Visibility.open);
    }

    @Test
    public void shouldClosePreviousPageWhenOpenAPage() {
	Page page1 = createPage(Visibility.open);
	logic.add(page1);
	Page page2 = createPage(Visibility.open);
	logic.add(page2);
	verify(page1).setVisibility(Visibility.closed);
    }

    @Test
    public void shouldShowIfNotadded() {
	Page pageIn = createPage(Visibility.open);
	logic.add(pageIn);
	verify(pageIn).setVisibility(Visibility.open);
	Page pageOut = createPage(Visibility.open);
	logic.show(pageOut);
	verify(pageIn, times(0)).setVisibility(Visibility.closed);
    }

    @Test
    public void shouldShowPreviousPageWhenHideAOpenedPage() {
	Page page1 = createPage(Visibility.open);
	logic.add(page1);
	verify(page1).setVisibility(Visibility.open);
	Page page2 = createPage(Visibility.open);
	logic.add(page2);
	verify(page2).setVisibility(Visibility.open);
	logic.hide(page2);

	verify(page2).setVisibility(Visibility.hidden);
	verify(page1, times(2)).setVisibility(Visibility.open);
    }

    private Page createPage(Visibility visibility) {
	Page page = mock(Page.class);
	when(page.getVisibility()).thenReturn(visibility);
	return page;
    }

    private void shouldAddPage(Visibility visibility) {
	Page page = createPage(visibility);
	logic.add(page);
	verify(view).addPage(page);
    }
}
