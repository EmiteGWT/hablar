package com.calclab.hablar.rooms.client.ui.invite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class InviteToRoomWidget extends Composite implements InviteToRoomDisplay {

    interface InviteToRoomWidgetUiBinder extends UiBinder<Widget, InviteToRoomWidget> {
    }

    private static InviteToRoomWidgetUiBinder uiBinder = GWT.create(InviteToRoomWidgetUiBinder.class);

    public InviteToRoomWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
