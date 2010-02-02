package com.calclab.hablar.editbuddy.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
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
    TextBox newNickName;
    @UiField
    Label oldNickName;

    private static EditBuddyWidgetUiBinder uiBinder = GWT.create(EditBuddyWidgetUiBinder.class);

    public EditBuddyWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getCancel() {
	return cancel;
    }

    @Override
    public HasChangeHandlers getEnterAction() {
	return newNickName;
    }

    @Override
    public Focusable getFirstFocusable() {
	return newNickName;
    }

    @Override
    public HasText getNewNickName() {
	return newNickName;
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
