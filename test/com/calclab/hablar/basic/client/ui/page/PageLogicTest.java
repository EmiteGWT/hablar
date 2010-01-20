package com.calclab.hablar.basic.client.ui.page;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;

public class PageLogicTest {

    private PageView view;
    private PageLogic logic;
    private PageHeader header;

    @Before
    public void setup() {
	header = mock(PageHeader.class);
	view = mock(PageView.class);
	when(view.getHeader()).thenReturn(header);
	logic = new PageLogic(view, Visibility.open);
    }

    @Test
    public void shouldRequestFocusWhenMessageAndClosed() {
	logic.setVisibility(Visibility.closed);
	logic.setStatusMessage("new status");
	verify(header).requestFocus();
    }

    @Test
    public void shouldRequestFocusWhenMessageAndHidden() {
	logic.setVisibility(Visibility.hidden);
	logic.setStatusMessage("new status");
	verify(header).requestFocus();
    }

}
