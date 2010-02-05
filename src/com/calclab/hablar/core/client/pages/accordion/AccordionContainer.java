package com.calclab.hablar.core.client.pages.accordion;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.core.client.pages.MainContainer;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class AccordionContainer extends MainContainer {
    private static final double SIZE_HEADER = 24;
    private Widget lastFocusWidget;

    public AccordionContainer(LayoutPanel parent) {
	super(new AccordionPanel(), parent);
    }

    @Override
    public boolean unfocus(Page<?> page) {
	if (lastFocusWidget != null) {
	    ((AccordionPanel) container).showWidget(lastFocusWidget);
	    focus(container, lastFocusWidget);
	    return true;
	}
	return false;
    }

    @Override
    protected void add(Widget container, Widget pageWidget, Widget headerWidget) {
	((AccordionPanel) container).add(pageWidget, headerWidget, SIZE_HEADER);
    }

    @Override
    protected HeaderDisplay createHeaderDisplay(Page<?> page) {
	return new AccordionHeaderWidget(page.getId());
    }

    @Override
    protected void focus(Widget container, Widget pageWidget) {
	lastFocusWidget = pageWidget;
	((AccordionPanel) container).showWidget(pageWidget);
    }
}
