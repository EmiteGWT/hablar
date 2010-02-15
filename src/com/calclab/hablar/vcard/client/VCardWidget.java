package com.calclab.hablar.vcard.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class VCardWidget extends Composite implements VCardDisplay {

    interface VCardWidgetUiBinder extends UiBinder<Widget, VCardWidget> {
    }

    private static VCardWidgetUiBinder uiBinder = GWT.create(VCardWidgetUiBinder.class);

    @UiField
    TextBox nickName, firstName, middleName, surname, email, organizationName;
    @UiField
    Button accept, cancel;
    @UiField
    SpanElement title;

    public VCardWidget(final boolean readOnly) {
	initWidget(uiBinder.createAndBindUi(this));
	setReadOnly(readOnly);
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

    @Override
    public void setAcceptVisible(final boolean visible) {
	accept.setVisible(visible);
    }

    @Override
    public void setCancelText(final String text) {
	cancel.setText(text);
    }

    @Override
    public void setCancelVisible(final boolean visible) {
	cancel.setVisible(visible);
    }

    @Override
    public void setPageTitle(final String titleText) {
	title.setInnerText(titleText);
    }

    public void setReadOnly(final boolean readOnly) {
	nickName.setReadOnly(readOnly);
	firstName.setReadOnly(readOnly);
	middleName.setReadOnly(readOnly);
	surname.setReadOnly(readOnly);
	email.setReadOnly(readOnly);
	organizationName.setReadOnly(readOnly);
    }

}
