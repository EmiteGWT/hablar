package com.calclab.hablar.core.client.pages.tabs;

import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TabsHeaderWidget extends Composite implements HeaderDisplay {

    interface TabsHeaderWidgetUiBinder extends UiBinder<Widget, TabsHeaderWidget> {
    }

    private static TabsHeaderWidgetUiBinder uiBinder = GWT.create(TabsHeaderWidgetUiBinder.class);

    @UiField
    FocusPanel self;
    @UiField
    Label title;
    @UiField
    Image close;
    @UiField
    Image icon;

    public TabsHeaderWidget(final String id) {
	this(id, "24px", "120px");
    }

    public TabsHeaderWidget(final String id, String height, String width) {
	initWidget(uiBinder.createAndBindUi(this));
	this.setHeight(height);
	this.setWidth(width);
	ensureDebugId("HeaderWidget-" + id);
	// title.ensureDebugId(Idify.id("HeaderWidget", id, "title"));
    }

    @Override
    public void setIcon(final ImageResource iconStyle) {
	icon.setResource(iconStyle);
    }

    @Override
    public void addStyle(final String styleName) {
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
	return self;
    }

    @Override
    public void removeStyle(final String styleName) {
	self.removeStyleName(styleName);
    }

    @Override
    public void setCloseIconVisible(final boolean visible) {
	close.setVisible(visible);
    }

    @Override
    public void setHeaderTooltip(final String tooltip) {
	title.setTitle(tooltip);
	icon.setTitle(tooltip);
    }
}
