package com.calclab.hablar.vcard.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class VCardWidget extends Composite implements VCardDisplay {

    interface VCardWidgetUiBinder extends UiBinder<Widget, VCardWidget> {
    }

    private static VCardWidgetUiBinder uiBinder = GWT.create(VCardWidgetUiBinder.class);

    @UiField
    TextBox nickName, firstName, middleName, surname, email, organizationName;

    public VCardWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public TextBox getEmail() {
	return email;
    }

    @Override
    public TextBox getFirstName() {
	return firstName;
    }

    @Override
    public TextBox getMiddleName() {
	return middleName;
    }

    @Override
    public TextBox getNickName() {
	return nickName;
    }

    @Override
    public TextBox getOrganizationName() {
	return organizationName;
    }

    @Override
    public TextBox getSurname() {
	return surname;
    }

}
