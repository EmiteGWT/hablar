package com.calclab.hablar.rooms.client.open;

import java.util.Collection;
import java.util.List;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.selectionlist.DoubleList;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.core.client.validators.HasState;
import com.calclab.hablar.rooms.client.RoomsMessages;
import com.calclab.hablar.roster.client.selection.DoubleListRosterItemSelector;
import com.calclab.hablar.roster.client.selection.RosterItemSelector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditRoomWidget extends Composite implements EditRoomDisplay {

    private static RoomsMessages messages;

    public static void setMessages(final RoomsMessages messages) {
	EditRoomWidget.messages = messages;
    }

    public static RoomsMessages i18n() {
	return messages;
    }

    interface InviteToRoomWidgetUiBinder extends UiBinder<Widget, EditRoomWidget> {
    }

    private static InviteToRoomWidgetUiBinder uiBinder = GWT.create(InviteToRoomWidgetUiBinder.class);

    @UiField
    Button accept, cancel;
    @UiField
    DoubleList selectionList;
    @UiField
    TextBox message, roomName;
    @UiField
    LabelElement groupChatNameLabel, invitationLabel, occupantsLabel;
    @UiField
    Label roomNameError;

    @UiField
    SpanElement title;

    private RosterItemSelector selector;

    public EditRoomWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	roomName.ensureDebugId("InviteToRoomWidget-roomName");
	message.ensureDebugId("InviteToRoomWidget-message");
	accept.ensureDebugId("InviteToRoomWidget-invite");
	cancel.ensureDebugId("InviteToRoomWidget-cancel");
	selectionList.ensureDebugId("InviteToRoomWidget-list");
	selector = new DoubleListRosterItemSelector(selectionList);
	groupChatNameLabel.setInnerText(i18n().groupChatNameLabelText());
	invitationLabel.setInnerText(i18n().invitationMessageLabelText());
	occupantsLabel.setInnerText(i18n().occupantsLabelText());
	cancel.setText(i18n().cancelAction());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clearList() {
	clearSelectionList();
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
    public HasValue<String> getRoomName() {
	return roomName;
    }

    @Override
    public HasValue<List<Selectable>> getSelectionList() {
	return selectionList;
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

    @Override
    public void addRosterItem(RosterItem rosterItem) {
	selector.addRosterItem(rosterItem);
    }

    @Override
    public void addSelectedRosterItem(RosterItem rosterItem) {
	selector.addSelectedRosterItem(rosterItem);
    }

    @Override
    public void clearSelectionList() {
	selector.clearSelectionList();
    }

    @Override
    public Collection<RosterItem> getSelectedItems() {
	return selector.getSelectedItems();
    }
}
