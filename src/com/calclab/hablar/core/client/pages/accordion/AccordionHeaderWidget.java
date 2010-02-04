package com.calclab.hablar.core.client.pages.accordion;

import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AccordionHeaderWidget extends Composite implements HeaderDisplay {

    interface AccordionHeaderWidgetUiBinder extends UiBinder<Widget, AccordionHeaderWidget> {
    }

    private static AccordionHeaderWidgetUiBinder uiBinder = GWT.create(AccordionHeaderWidgetUiBinder.class);

    @UiField
    FlowPanel self;

    @UiField
    Label title, icon, close;

    public AccordionHeaderWidget(String pageId) {
	initWidget(uiBinder.createAndBindUi(this));
	ensureDebugId("HeaderWidget-" + pageId);
    }

    @Override
    public void addIconStyle(String iconStyle) {
	icon.addStyleName(iconStyle);
    }

    @Override
    public void addStyle(String styleName) {
	self.addStyleName(styleName);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getClose() {
	return close;
    }

    @Override
    public HasText getHeaderTitle() {
	return title;
    }

    @Override
    public HasClickHandlers getOpen() {
	return title;
    }

    @Override
    public void removeIconStyle(String iconStyle) {
	icon.removeStyleName(iconStyle);
    }

    @Override
    public void removeStyle(String styleName) {
	self.removeStyleName(styleName);
    }

    @Override
    public void setCloseIconVisible(boolean visible) {
	close.setVisible(visible);
    }

}
