package com.calclab.hablar.basic.client.ui.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.basic.client.DefaultEventBus;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;

public class PagesLogicTest {
    private PagesPanel view;
    private PagesLogic logic;
    private DefaultEventBus eventBus;

    @Before
    public void setup() {
	eventBus = new DefaultEventBus();
	view = new PagesPanelTester();
	logic = new PagesLogic(eventBus, view);
    }

    @Test
    public void shouldAddClosedPages() {
	shouldAddPage(Visibility.notFocused);
    }

    @Test
    public void shouldAddOpenPages() {
	shouldAddPage(Visibility.focused);
    }

    @Test
    public void shouldCloseHiddenPages() {
	PageView pageView = createPageView(Visibility.closed);
	logic.add(pageView);
	logic.close(pageView);
	assertEquals(1, logic.getVisiblePages().size());
	assertEquals(0, logic.getHiddenPages().size());
	verify(pageView).setVisibility(Visibility.notFocused);

    }

    @Test
    public void shouldClosePreviousPageWhenOpenAPage() {
	PageView pageView1 = createPageView(Visibility.focused);
	logic.add(pageView1);
	PageView pageView2 = createPageView(Visibility.focused);
	logic.add(pageView2);
	verify(pageView1).setVisibility(Visibility.notFocused);
    }

    @Test
    public void shouldHidePages() {
	PageView page = createPageView(Visibility.focused);
	logic.add(page);
	assertEquals(1, logic.getVisiblePages().size());
	assertEquals(0, logic.getHiddenPages().size());
	assertEquals(Visibility.focused, page.getVisibility());
	logic.hide(page);
	assertEquals(0, logic.getVisiblePages().size());
	assertEquals(1, logic.getHiddenPages().size());
	verify(page).setVisibility(Visibility.closed);
    }

    @Test
    public void shouldNotOpenIfNotAddedBefore() {
	PageView pageViewIn = createPageView(Visibility.focused);
	logic.add(pageViewIn);
	verify(pageViewIn).setVisibility(Visibility.focused);
	PageView pageViewOut = createPageView(Visibility.focused);
	logic.open(pageViewOut);
	verify(pageViewIn, never()).setVisibility(Visibility.notFocused);
    }

    @Test
    public void shouldOpenHiddenPages() {
	PageView pageView = createPageView(Visibility.closed);
	logic.add(pageView);
	logic.open(pageView);
	assertEquals(1, logic.getVisiblePages().size());
	assertEquals(0, logic.getHiddenPages().size());
	verify(pageView).setVisibility(Visibility.focused);
    }

    @Test
    public void shouldRetrievePagesByType() {
	PageView page = createPageView(Visibility.focused);
	when(page.getPageType()).thenReturn("pageType");
	logic.add(page);
	List<PageView> pages = logic.getPagesOfType("pageType");
	assertEquals(1, pages.size());
	assertTrue(pages.contains(page));
    }

    @Test
    public void shouldRetrieveVisibleAndHiddenPages() {
	logic.add(createPageView(Visibility.focused));
	logic.add(createPageView(Visibility.focused));
	logic.add(createPageView(Visibility.closed));
	assertEquals(2, logic.getVisiblePages().size());
	assertEquals(1, logic.getHiddenPages().size());
    }

    @Test
    public void shouldSetOpenIfClosedPageAddedAsFirstPage() {
	PageView pageView = createPageView(Visibility.notFocused);
	logic.add(pageView);
	verify(pageView).setVisibility(Visibility.focused);
    }

    @Test
    public void shouldShowHiddenPageIfWhenStatus() {
	PageView page = createPageView(Visibility.closed);
	logic.add(page);
	logic.whenStatus(page);
	verify(page).setVisibility(Visibility.notFocused);
    }

    @Test
    public void shouldShowPreviousPageWhenHideAOpenedPage() {
	PageView pageView1 = createPageView(Visibility.focused);
	logic.add(pageView1);
	verify(pageView1).setVisibility(Visibility.focused);
	PageView pageView2 = createPageView(Visibility.focused);
	logic.add(pageView2);
	verify(pageView2).setVisibility(Visibility.focused);
	logic.hide(pageView2);

	verify(pageView2).setVisibility(Visibility.closed);
	verify(pageView1, times(2)).setVisibility(Visibility.focused);
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
