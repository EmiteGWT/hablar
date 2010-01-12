package com.calclab.hablar.client.ui.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The header of the page. The header has text, icon, close button (optional)
 * and active state. Each PageWidget has one PageHeader. The PageWidget creates
 * the header.
 */
public class PageHeader extends Composite {

    interface DefaultStyles extends HeaderStyles {
    }

    interface StackHeaderUiBinder extends UiBinder<Widget, PageHeader> {
    }

    private static StackHeaderUiBinder uiBinder = GWT.create(StackHeaderUiBinder.class);

    @UiField
    Label title, close, icon;
    @UiField
    FlowPanel header;

    @UiField
    DefaultStyles defaultStyles;

    private final PageWidget page;
    private boolean open;
    private String iconClass;

    private HeaderStyles currentStyles;

    public PageHeader(PageWidget page, boolean closeable) {
	this.page = page;
	Widget element = uiBinder.createAndBindUi(this);
	initWidget(element);
	close.setVisible(closeable);
	this.currentStyles = defaultStyles;
    }

    public void setActive(boolean active) {
	if (active && !open) {
	    header.getElement().addClassName(currentStyles.requestFocus());
	} else {
	    header.getElement().removeClassName(currentStyles.requestFocus());
	}
    }

    public void setIconClass(String iconClass) {
	if (this.iconClass != null) {
	    icon.getElement().removeClassName(this.iconClass);
	}
	icon.getElement().addClassName(iconClass);
	this.iconClass = iconClass;

    }

    public void setOpen(boolean open) {
	GWT.log("Page header open " + open, null);
	this.open = open;
	applyStyles();
    }

    public void setStyles(HeaderStyles styles) {
	this.currentStyles = styles;
	applyStyles();
    }

    private void applyStyles() {
	if (open) {
	    header.getElement().removeClassName(currentStyles.closed());
	    header.getElement().addClassName(currentStyles.open());
	} else {
	    header.getElement().removeClassName(currentStyles.open());
	    header.getElement().addClassName(currentStyles.closed());
	}
    }

    @UiHandler("title")
    void handleActivateTitle(ClickEvent event) {
	page.setOpen(true);
    }

    @UiHandler("close")
    void handleClose(ClickEvent event) {
	page.close();
    }

    void setHeaderTitle(String title) {
	this.title.setText(title);
    }

}
