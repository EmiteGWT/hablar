package com.calclab.hablar.basic.client.ui.page;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.basic.client.ui.DefaultEventBus;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;

public class PageLogicTest {

    private PageView view;
    private PageLogic logic;
    private PageHeader header;
    private DefaultEventBus eventBus;

    @Before
    public void setup() {
	eventBus = new DefaultEventBus();
	header = mock(PageHeader.class);
	view = mock(PageView.class);
	logic = new PageLogic(eventBus, view, header, Visibility.focused);
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
