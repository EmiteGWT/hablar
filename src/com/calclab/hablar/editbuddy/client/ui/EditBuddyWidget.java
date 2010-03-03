package com.calclab.hablar.editbuddy.client.ui;

import com.calclab.hablar.core.client.validators.HasState;
import com.google.gwt.core.client.GWT;
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

    interface EditBuddyWidgetUiBinder extends UiBinder<Widget, EditBuddyWidget> {
    }

    @UiField
    Button cancel, save;
    @UiField
    TextBox nickName;
    @UiField
    Label oldNickName, nickNameError;

    private static EditBuddyWidgetUiBinder uiBinder = GWT.create(EditBuddyWidgetUiBinder.class);

    public EditBuddyWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	nickName.ensureDebugId("EditBuddyWidget-nickname");
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
