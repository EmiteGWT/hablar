package com.calclab.hablar.client.ui.pages;

import com.calclab.hablar.client.ui.page.HeaderStyles;
import com.calclab.hablar.client.ui.page.Page;
import com.calclab.hablar.client.ui.page.PageHeader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

/**
 * A StackPanel with a status and more control over the current opened widgets.
 * Can add and remove PageWidgets. Can retrieve the current visible page and
 * fire events when a page is open or close
 * 
 */
public class AccordionPages extends AbstractPages {
    interface HablarWidgetUiBinder extends UiBinder<Widget, AccordionPages> {
    }
    interface HeaderStyle extends HeaderStyles {

    }

    private static HablarWidgetUiBinder uiBinder = GWT.create(HablarWidgetUiBinder.class);

    @UiField
    HeaderStyle headerStyle;
    @UiField
    AccordionPanel accordion;

    public AccordionPages() {
	super();
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public boolean hasPage(Page page) {
	return accordion.hasWidget((Widget) page);
    }

    @Override
    protected void addPage(Page page) {
	PageHeader header = page.getHeader();
	header.setStyles(headerStyle);
	accordion.add((Widget) page, header, 24);
    }

    @Override
    protected void removePage(Page page) {
	accordion.remove((Widget) page);
    }

    @Override
    protected void showPage(Page page) {
	accordion.showWidget((Widget) page);
    }

}
