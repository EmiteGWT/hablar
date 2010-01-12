package com.calclab.hablar.client.login;

import com.calclab.hablar.client.pages.PageWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginPage extends PageWidget {

    interface Icons extends CssResource {
	String loggedInIcon();

	String loggedOutIcon();
    }

    interface LoginWidgetUiBinder extends UiBinder<Widget, LoginPage> {
    }

    private static LoginWidgetUiBinder uiBinder = GWT.create(LoginWidgetUiBinder.class);

    @UiField
    Button button;
    @UiField
    TextBox uri;
    @UiField
    PasswordTextBox password;
    @UiField
    FlowPanel output;

    @UiField
    Icons icons;

    private final LoginLogic logic;

    public LoginPage() {
	super(false);
	initWidget(uiBinder.createAndBindUi(this));
	this.logic = new LoginLogic(this);
    }

    public void addMessage(String string) {
	output.add(new StatusMessage(string));
    }

    public String getPassword() {
	return password.getText();
    }

    public String getUserName() {
	return uri.getText();
    }

    public void setActionEnabled(boolean enabled) {
	button.setEnabled(enabled);
    }

    public void setActionText(String text) {
	button.setText(text);
    }

    public void setUserName(String userName) {
	uri.setText(userName);
	uri.setFocus(true);
    }

    @UiHandler("button")
    void onClick(ClickEvent e) {
	logic.onAction();
    }

    @UiHandler("uri")
    void onNameChanged(ChangeEvent event) {
	password.setFocus(true);
    }

    @UiHandler("password")
    void onPasswordChanged(ChangeEvent event) {
	logic.onAction();
    }

}
