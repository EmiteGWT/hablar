package com.calclab.hablar.core.client.pages.accordion;

import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AccordionHeaderWidget extends Composite implements HeaderDisplay {

    interface AccordionHeaderWidgetUiBinder extends UiBinder<Widget, AccordionHeaderWidget> {
    }

    private static AccordionHeaderWidgetUiBinder uiBinder = GWT.create(AccordionHeaderWidgetUiBinder.class);

    @UiField
    FlowPanel flow;

    @UiField
    FocusPanel self;

    @UiField
    Label title;

    @UiField
    Image icon, close;

    public AccordionHeaderWidget(final String pageId) {
	initWidget(uiBinder.createAndBindUi(this));
	ensureDebugId("HeaderWidget-" + pageId);
	close.ensureDebugId("HeaderWidget-" + pageId + "-close");
    }

    @Override
    public void setIcon(final ImageResource iconStyle) {
	icon.setResource(iconStyle);
    }

    @Override
    public void addStyle(final String styleName) {
	flow.addStyleName(styleName);
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
	flow.removeStyleName(styleName);
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
