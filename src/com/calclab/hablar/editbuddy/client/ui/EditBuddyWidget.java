package com.calclab.hablar.editbuddy.client.ui;

import com.calclab.hablar.core.client.validators.HasState;
import com.calclab.hablar.editbuddy.client.EditBuddyMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditBuddyWidget extends Composite implements EditBuddyDisplay {

    private static EditBuddyMessages messages;

    public static void setMessages(final EditBuddyMessages messages) {
	EditBuddyWidget.messages = messages;
    }

    public static EditBuddyMessages i18n() {
	return messages;
    }

    interface EditBuddyWidgetUiBinder extends UiBinder<Widget, EditBuddyWidget> {
    }

    @UiField
    SpanElement title;
    @UiField
    Button cancel, save;
    @UiField
    TextBox nickName;
    @UiField
    Label oldNickName, nickNameError;
    @UiField
    LabelElement changeFromLabel, changeToLabel;

    private static EditBuddyWidgetUiBinder uiBinder = GWT.create(EditBuddyWidgetUiBinder.class);

    public EditBuddyWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	nickName.ensureDebugId("EditBuddyWidget-nickname");
	save.ensureDebugId("EditBuddyWidget-save");
	title.setInnerText(i18n().changeBuddyNickname());
	changeFromLabel.setInnerText(i18n().changeFromLabelText());
	changeToLabel.setInnerText(i18n().changeToLabelText());
	save.setText(i18n().acceptAction());
	cancel.setText(i18n().cancelAction());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasState<Boolean> getAcceptState() {
	return new HasState<Boolean>() {
	    @Override
	    public void setState(final Boolean state) {
		save.setEnabled(state);
	    }
	};
    }

    @Override
    public HasClickHandlers getCancel() {
	return cancel;
    }

    @Override
    public HasChangeHandlers getEnterAction() {
	return nickName;
    }

    @Override
    public Focusable getFirstFocusable() {
	return nickName;
    }

    @Override
    public HasText getNickName() {
	return nickName;
    }

    @Override
    public HasText getNickNameError() {
	return nickNameError;
    }

    @Override
    public HasKeyDownHandlers getNickNameKeys() {
	return nickName;
    }

    @Override
    public HasText getOldNickName() {
	return oldNickName;
    }

    @Override
    public HasClickHandlers getSave() {
	return save;
    }

}
