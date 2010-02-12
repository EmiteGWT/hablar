package com.calclab.hablar.rooms.client.ui.open;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OpenRoomWidget extends Composite implements OpenRoomDisplay {

    interface InviteToRoomWidgetUiBinder extends UiBinder<Widget, OpenRoomWidget> {
    }

    private static InviteToRoomWidgetUiBinder uiBinder = GWT.create(InviteToRoomWidgetUiBinder.class);

    @UiField
    Button accept, cancel;
    @UiField
    FlowPanel list;
    @UiField
    TextBox message, roomName;

    @UiField
    SpanElement title;

    public OpenRoomWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	accept.ensureDebugId("InviteToRoomWidget-invite");
	cancel.ensureDebugId("InviteToRoomWidget-cancel");
	list.ensureDebugId("InviteToRoomWidget-list");
    }

    @Override
    public void addItem(final SelectRosterItemDisplay itemDisplay) {
	list.add(itemDisplay.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clearList() {
	list.clear();
    }

    @Override
    public SelectRosterItemDisplay createItem() {
	return new SelectRosterItemWidget();
    }

    @Override
    public HasClickHandlers getCancel() {
	return cancel;
    }

    @Override
    public HasClickHandlers getInvite() {
	return accept;
    }

    @Override
    public HasText getMessage() {
	return message;
    }

    @Override
    public HasText getRoomName() {
	return roomName;
    }

    @Override
    public void setAcceptText(final String text) {
	accept.setText(text);
    }

    @Override
    public void setPageTitle(final String text) {
	title.setInnerText(text);
    }

}
