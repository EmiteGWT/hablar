package com.calclab.hablar.client.ui.pages;

import static org.mockito.Mockito.mock;
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

    public void shouldHidePage() {
	Page page = mock(Page.class);
	logic.add(page);
	logic.hide(page);

    }

    private void shouldAddPage(Visibility visibility) {
	Page page = mock(Page.class);
	when(page.getVisibility()).thenReturn(visibility);
	logic.add(page);
	verify(view).addPage(page);
    }
}
