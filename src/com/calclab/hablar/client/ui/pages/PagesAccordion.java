package com.calclab.hablar.client.ui.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A StackPanel with a status and more control over the current opened widgets.
 * Can add and remove PageWidgets. Can retrieve the current visible page and
 * fire events when a page is open or close
 * 
 */
public class PagesAccordion extends PagesWidget {
    interface HablarWidgetUiBinder extends UiBinder<Widget, PagesAccordion> {
    }
    interface HeaderStyle extends HeaderStyles {

    }

    private static HablarWidgetUiBinder uiBinder = GWT.create(HablarWidgetUiBinder.class);

    @UiField
    HeaderStyle headerStyle;
    @UiField
    StackLayoutPanel stack;

    public PagesAccordion() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void remove(PageWidget page) {
	stack.remove(page);
    }

    @Override
    protected void addPage(PageWidget page) {
	PageHeader header = page.getHeader();
	header.setStyles(headerStyle);
	stack.add(page, header, 24);
    }

    @Override
    protected void showPage(PageWidget page) {
	stack.showWidget(page);
    }

}
