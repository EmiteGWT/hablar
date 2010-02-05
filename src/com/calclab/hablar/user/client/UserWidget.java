package com.calclab.hablar.user.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class UserWidget extends Composite implements UserDisplay {

    interface UserWidgetUiBinder extends UiBinder<Widget, UserWidget> {
    }

    private static UserWidgetUiBinder uiBinder = GWT.create(UserWidgetUiBinder.class);

    @UiField
    TextBox status;

    public UserWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	status.ensureDebugId("UserWidget-status");
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getStatus() {
	return status;
    }

}
