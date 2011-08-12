package com.calclab.hablar.dock.client;

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

public class DockHeaderWidget extends Composite implements HeaderDisplay {

	interface DockHeaderWidgetUiBinder extends UiBinder<Widget, DockHeaderWidget> {
	}

	private static DockHeaderWidgetUiBinder uiBinder = GWT.create(DockHeaderWidgetUiBinder.class);
	@UiField
	FlowPanel flow;

	@UiField
	FocusPanel self;

	@UiField
	Label title;

	@UiField
	Image icon, close;

	public DockHeaderWidget(final String pageId, final String position) {
		initWidget(uiBinder.createAndBindUi(this));
		ensureDebugId("HeaderWidget-" + pageId);
		flow.addStyleName("DockHeaderWidget-" + position);
	}

	@Override
	public void addStyle(final String styleName) {
		flow.addStyleName(styleName);
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

	@Override
	public void setIcon(final ImageResource iconResource) {
		icon.setResource(iconResource);
	}
}
