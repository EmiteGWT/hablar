package com.calclab.hablar.login.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends Composite implements LoginDisplay {

    private static LoginMessages messages;

    public static void setMessages(final LoginMessages messages) {
	LoginWidget.messages = messages;
    }

    public static LoginMessages i18n() {
	return messages;
    }

    interface LoginWidgetUiBinder extends UiBinder<Widget, LoginWidget> {
    }

    private static LoginWidgetUiBinder uiBinder = GWT.create(LoginWidgetUiBinder.class);

    @UiField
    Button button;

    @UiField
    TextBox user, password;

    @UiField
    FlowPanel output;

    @UiField
    LabelElement userLabel, passwordLabel;

    public LoginWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	button.ensureDebugId("LoginWidget-button");
	user.ensureDebugId("LoginWidget-user");
	password.ensureDebugId("LoginWidget-password");
	output.ensureDebugId("LoginWidget-output");
	userLabel.setInnerText(i18n().userLabelText());
	passwordLabel.setInnerText(i18n().passwordLabelText());
    }

    @Override
    public void addMessage(String message) {
	output.add(new Label(message));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getAction() {
	return button;
    }

    @Override
    public HasText getActionText() {
	return button;
    }

    @Override
    public HasText getPassword() {
	return password;
    }

    @Override
    public HasText getUser() {
	return user;
    }

    @Override
    public void setActionEnabled(boolean enabled) {
	button.setEnabled(enabled);
    }

}
