package com.calclab.hablar.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
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

    interface StackHeaderUiBinder extends UiBinder<Widget, PageHeader> {
    }

    interface Styles extends CssResource {
	public String active();

	public String open();
    }

    private static StackHeaderUiBinder uiBinder = GWT.create(StackHeaderUiBinder.class);

    @UiField
    Label title, close, icon;
    @UiField
    FlowPanel header;

    @UiField
    Styles style;

    private final PageWidget page;
    private boolean open;
    private String iconClass;

    private HeaderStyles styles;

    public PageHeader(PageWidget page, boolean closeable) {
	this.page = page;
	Widget element = uiBinder.createAndBindUi(this);
	initWidget(element);
	close.setVisible(closeable);
    }

    @UiHandler("title")
    public void handleActivateTitle(ClickEvent event) {
	page.setOpen(true);
    }

    @UiHandler("close")
    public void handleClose(ClickEvent event) {
	page.close();
    }

    public void setActive(boolean active) {
	if (active && !open) {
	    header.getElement().addClassName(style.active());
	} else {
	    header.getElement().removeClassName(style.active());
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
	this.open = open;
	if (open)
	    header.getElement().addClassName(style.open());
	else
	    header.getElement().removeClassName(style.open());
    }

    public void setStyles(HeaderStyles styles) {
	if (this.styles != null) {
	}
	setIconClass(styles.defaultIcon());
	this.styles = styles;
    }

    void setHeaderTitle(String title) {
	this.title.setText(title);
    }

}
