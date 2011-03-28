package com.calclab.hablar.core.client.pages.accordion;

import com.calclab.hablar.core.client.HablarDisplay;
import com.calclab.hablar.core.client.container.main.MainLayout;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.google.gwt.user.client.ui.Widget;

public class AccordionLayout extends MainLayout {
    private static final double HEAD_SIZE = 24;
    private static AccordionPanel accordion;

    public AccordionLayout(HablarDisplay display) {
	super(accordion = new AccordionPanel(), display);
    }

    @Override
    public void add(Widget pageWidget, Widget headWidget) {
	accordion.add(pageWidget, headWidget, HEAD_SIZE);
    }

    @Override
    public HeaderDisplay createHeaderDisplay(Page<?> page) {
	return new AccordionHeaderWidget(page.getId());
    }

    @Override
    public void focus(Widget pageWidget) {
	accordion.showWidget(pageWidget);
    }

    @Override
    public void remove(Widget pageWidget) {
	accordion.remove(pageWidget);
    }
}
