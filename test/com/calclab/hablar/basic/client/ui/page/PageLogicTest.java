package com.calclab.hablar.basic.client.ui.page;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.testing.EmiteTester;

public class PageLogicTest {

    private PageView view;
    private PageLogic logic;
    private PageHeader header;

    @Before
    public void setup() {
	EmiteTester tester = new EmiteTester();
	header = tester.mockDisplay(PageHeader.class);
	view = mock(PageView.class);
	when(view.getHeader()).thenReturn(header);
	logic = new PageLogic(tester.eventBus, view, Visibility.focused);
    }

    @Test
    public void shouldRequestFocusWhenMessageAndClosed() {
	logic.setVisibility(Visibility.notFocused);
	logic.setStatusMessage("new status");
	verify(header).requestFocus();
    }

    @Test
    public void shouldRequestFocusWhenMessageAndHidden() {
	logic.setVisibility(Visibility.closed);
	logic.setStatusMessage("new status");
	verify(header).requestFocus();
    }

}
