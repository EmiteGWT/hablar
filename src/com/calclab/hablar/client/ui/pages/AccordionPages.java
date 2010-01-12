package com.calclab.hablar.client.ui.pages;

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
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void removePage(PageWidget page) {
	accordion.remove(page);
    }

    @Override
    protected void addPage(PageWidget page, Position position) {
	PageHeader header = page.getHeader();
	header.setStyles(headerStyle);
	accordion.add(page, header, 24);
    }

    @Override
    protected void showPage(PageWidget page) {
	accordion.showWidget(page);
    }

}
