package com.calclab.hablar.client.login;

import com.calclab.hablar.client.ui.page.PageWidget;
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

    public static final String ID = "LoginPage";
    public static final String URI = ID + "-uri";
    public static final String PASSWD = ID + "-passwd";
    public static final String BTN = ID + "-btn";

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

    public LoginPage(Visibility visibility) {
	super(visibility, false);
	// FIXME: better this can go in the constructor
	super.setId(ID);
	initWidget(uiBinder.createAndBindUi(this));
	uri.ensureDebugId(URI);
	password.ensureDebugId(PASSWD);
	button.ensureDebugId(BTN);
	this.logic = new LoginLogic(this);
    }

    public void addMessage(final String string) {
	output.add(new StatusMessage(string));
    }

    public String getPassword() {
	return password.getText();
    }

    public String getUserName() {
	return uri.getText();
    }

    public void setActionEnabled(final boolean enabled) {
	button.setEnabled(enabled);
    }

    public void setActionText(final String text) {
	button.setText(text);
    }

    public void setPassword(final String password) {
	this.password.setText(password);
    }

    public void setUserName(final String userName) {
	uri.setText(userName);
	uri.setFocus(true);
    }

    @UiHandler("button")
    void onClick(final ClickEvent e) {
	logic.onAction();
    }

    @UiHandler("uri")
    void onNameChanged(final ChangeEvent event) {
	password.setFocus(true);
    }

    @UiHandler("password")
    void onPasswordChanged(final ChangeEvent event) {
	logic.onAction();
    }

}
