package com.calclab.hablar.rooms.client.open;

import com.calclab.hablar.core.client.validators.HasState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditRoomWidget extends Composite implements EditRoomDisplay {

    interface InviteToRoomWidgetUiBinder extends UiBinder<Widget, EditRoomWidget> {
    }

    private static InviteToRoomWidgetUiBinder uiBinder = GWT.create(InviteToRoomWidgetUiBinder.class);

    @UiField
    Button accept, cancel;
    @UiField
    FlowPanel occupantsList;
    @UiField
    TextBox message, roomName;
    @UiField
    LabelElement occupantsLabel;
    @UiField
    Label roomNameError;

    @UiField
    SpanElement title;

    public EditRoomWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	accept.ensureDebugId("InviteToRoomWidget-invite");
	cancel.ensureDebugId("InviteToRoomWidget-cancel");
	occupantsList.ensureDebugId("InviteToRoomWidget-list");
    }

    @Override
    public void addItem(final SelectRosterItemDisplay itemDisplay) {
	occupantsList.add(itemDisplay.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clearList() {
	occupantsList.clear();
    }

    @Override
    public SelectRosterItemDisplay createItem() {
	return new SelectRosterItemWidget();
    }

    @Override
    public HasState<Boolean> getAcceptEnabled() {
	return new HasState<Boolean>() {
	    @Override
	    public void setState(final Boolean state) {
		accept.setEnabled(state);
	    }
	};
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
    public HasText getRoomNameError() {
	return roomNameError;
    }

    @Override
    public HasKeyDownHandlers getRoomNameKeys() {
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

    @Override
    public void setRoomNameEnabled(final boolean enabled) {
	roomName.setEnabled(enabled);
    }

}
