package com.calclab.hablar.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The header of the page. The header has text, icon, close button (optional)
 * and active state
 */
public class PageHeader extends Composite {

    private static StackHeaderUiBinder uiBinder = GWT.create(StackHeaderUiBinder.class);

    interface StackHeaderUiBinder extends UiBinder<Widget, PageHeader> {
    }

    @UiField
    Label title;
    
    @UiField
    Label close;
    
    @UiField
    DivElement header;

    private final PageWidget page;

    public PageHeader(PageWidget page, boolean closeable) {
	this.page = page;
	Widget element = uiBinder.createAndBindUi(this);
	initWidget(element);
	close.setVisible(closeable);
    }

    void setHeaderTitle(String title) {
	this.title.setText(title);
    }

    public void setActive(boolean active) {
    }
    
    @UiHandler("close")
    public void handleClose(ClickEvent event) {
	page.close();
    }

}
