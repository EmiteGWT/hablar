package com.calclab.hablar.dock.client;

import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.dock.client.DockConfig.Position;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DockHeaderWidget extends Composite implements HeaderDisplay {

    interface DockHeaderWidgetUiBinder extends UiBinder<Widget, DockHeaderWidget> {
    }

    private static DockHeaderWidgetUiBinder uiBinder = GWT.create(DockHeaderWidgetUiBinder.class);
    @UiField
    FlowPanel self;

    @UiField
    Label title, icon, close;

    public DockHeaderWidget(final String pageId, final Position position) {
	initWidget(uiBinder.createAndBindUi(this));
	ensureDebugId("HeaderWidget-" + pageId);
	addStyleName("DockHeaderWidget-" + position);
    }

    @Override
    public void addIconStyle(final String iconStyle) {
	icon.addStyleName(iconStyle);
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
	return title;
    }

    @Override
    public void removeIconStyle(final String iconStyle) {
	icon.removeStyleName(iconStyle);
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
