package com.calclab.hablar.basic.client.ui.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;

public class PagesLogicTest {
    private PagesPanel view;
    private PagesLogic logic;

    @Before
    public void setup() {
	view = new PagesPanelTester();
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
    public void shouldCloseHiddenPages() {
	PageView pageView = createPageView(Visibility.hidden);
	logic.add(pageView);
	logic.close(pageView);
	assertEquals(1, logic.getVisiblePages().size());
	assertEquals(0, logic.getHiddenPages().size());
	verify(pageView).setVisibility(Visibility.closed);

    }

    @Test
    public void shouldClosePreviousPageWhenOpenAPage() {
	PageView pageView1 = createPageView(Visibility.open);
	logic.add(pageView1);
	PageView pageView2 = createPageView(Visibility.open);
	logic.add(pageView2);
	verify(pageView1).setVisibility(Visibility.closed);
    }

    @Test
    public void shouldHidePages() {
	PageView page = createPageView(Visibility.open);
	logic.add(page);
	assertEquals(1, logic.getVisiblePages().size());
	assertEquals(0, logic.getHiddenPages().size());
	assertEquals(Visibility.open, page.getVisibility());
	logic.hide(page);
	assertEquals(0, logic.getVisiblePages().size());
	assertEquals(1, logic.getHiddenPages().size());
	verify(page).setVisibility(Visibility.hidden);
    }

    @Test
    public void shouldNotOpenIfNotAddedBefore() {
	PageView pageViewIn = createPageView(Visibility.open);
	logic.add(pageViewIn);
	verify(pageViewIn).setVisibility(Visibility.open);
	PageView pageViewOut = createPageView(Visibility.open);
	logic.open(pageViewOut);
	verify(pageViewIn, never()).setVisibility(Visibility.closed);
    }

    @Test
    public void shouldOpenHiddenPages() {
	PageView pageView = createPageView(Visibility.hidden);
	logic.add(pageView);
	logic.open(pageView);
	assertEquals(1, logic.getVisiblePages().size());
	assertEquals(0, logic.getHiddenPages().size());
	verify(pageView).setVisibility(Visibility.open);
    }

    @Test
    public void shouldRetrieveVisibleAndHiddenPages() {
	logic.add(createPageView(Visibility.open));
	logic.add(createPageView(Visibility.open));
	logic.add(createPageView(Visibility.hidden));
	assertEquals(2, logic.getVisiblePages().size());
	assertEquals(1, logic.getHiddenPages().size());
    }

    @Test
    public void shouldShowHiddenPageIfWhenStatus() {
	PageView page = createPageView(Visibility.hidden);
	logic.add(page);
	logic.whenStatus(page);
	verify(page).setVisibility(Visibility.closed);
    }

    @Test
    public void shouldShowPreviousPageWhenHideAOpenedPage() {
	PageView pageView1 = createPageView(Visibility.open);
	logic.add(pageView1);
	verify(pageView1).setVisibility(Visibility.open);
	PageView pageView2 = createPageView(Visibility.open);
	logic.add(pageView2);
	verify(pageView2).setVisibility(Visibility.open);
	logic.hide(pageView2);

	verify(pageView2).setVisibility(Visibility.hidden);
	verify(pageView1, times(2)).setVisibility(Visibility.open);
    }

    private PageView createPageView(Visibility visibility) {
	PageView pageView = mock(PageView.class);
	when(pageView.getVisibility()).thenReturn(visibility);
	return pageView;
    }

    private void shouldAddPage(Visibility visibility) {
	PageView pageView = createPageView(visibility);
	logic.add(pageView);
	assertTrue(view.hasPageView(pageView));
    }
}
