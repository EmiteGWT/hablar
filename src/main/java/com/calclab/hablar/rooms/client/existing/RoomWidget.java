package com.calclab.hablar.rooms.client.existing;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RoomWidget extends Composite {

	private static RoomWidgetUiBinder uiBinder = GWT.create(RoomWidgetUiBinder.class);

	@UiField
	FocusPanel self;

	@UiField
	Label name;

	interface RoomWidgetUiBinder extends UiBinder<Widget, RoomWidget> {
	}

	@UiConstructor
	public RoomWidget(String name) {
		initWidget(uiBinder.createAndBindUi(this));
		this.name.setText(name);
	}

	public HasClickHandlers getAction() {
		return self;
	}
}
