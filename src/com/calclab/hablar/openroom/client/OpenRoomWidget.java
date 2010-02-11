package com.calclab.hablar.openroom.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OpenRoomWidget extends Composite implements OpenRoomDisplay {

    interface OpenRoomWidgetUiBinder extends UiBinder<Widget, OpenRoomWidget> {
    }

    private static OpenRoomWidgetUiBinder uiBinder = GWT.create(OpenRoomWidgetUiBinder.class);

    @UiField
    Button open, cancel;
    @UiField
    TextBox roomName;

    public OpenRoomWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	open.ensureDebugId("OpenRoomWidget-open");
	cancel.ensureDebugId("OpenRoomWidget-cancel");
	roomName.ensureDebugId("OpenRoomWidget-roomName");
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    public Button getCancel() {
	return cancel;
    }

    public Button getOpen() {
	return open;
    }

    public TextBox getRoomName() {
	return roomName;
    }
}
