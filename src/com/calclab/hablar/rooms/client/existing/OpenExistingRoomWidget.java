package com.calclab.hablar.rooms.client.existing;

import java.util.List;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.hablar.rooms.client.RoomsMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class OpenExistingRoomWidget extends Composite implements OpenExistingRoomDisplay {

    private static OpenExistingRoomWidgetUiBinder uiBinder = GWT.create(OpenExistingRoomWidgetUiBinder.class);

    private static RoomsMessages messages;

    public static void setMessages(final RoomsMessages messages) {
	OpenExistingRoomWidget.messages = messages;
    }

    public static RoomsMessages i18n() {
	return messages;
    }

    @UiField
    SpanElement title;

    @UiField
    LabelElement availableRoomsLabel;

    @UiField
    ListBox roomList;

    @UiField
    Button accept, cancel;

    private List<XmppURI> rooms;

    interface OpenExistingRoomWidgetUiBinder extends UiBinder<Widget, OpenExistingRoomWidget> {
    }

    public OpenExistingRoomWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	title.setInnerText(i18n().openExistingRoom());
	availableRoomsLabel.setInnerText(i18n().availableRoomsLabelText());
	accept.setText(i18n().acceptAction());
	cancel.setText(i18n().cancelAction());
    }

    @Override
    public void setRooms(List<XmppURI> rooms) {
	this.rooms = rooms;
	roomList.clear();
	for (XmppURI uri : rooms) {
	    roomList.addItem(uri.getNode(), uri.toString());
	}
    }

    @Override
    public XmppURI getSelectedRoom() {
	int index = roomList.getSelectedIndex();
	if (index >= 0) {
	    return rooms.get(index);
	}
	return null;
    }

    @Override
    public HasClickHandlers getAccept() {
	return accept;
    }

    @Override
    public HasClickHandlers getCancel() {
	return cancel;
    }

    @Override
    public Widget asWidget() {
	return this;
    }
}
