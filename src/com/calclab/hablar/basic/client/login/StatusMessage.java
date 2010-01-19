package com.calclab.hablar.basic.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class StatusMessage extends Composite {
    private static MessageUiBinder uiBinder = GWT.create(MessageUiBinder.class);

    interface MessageUiBinder extends UiBinder<Widget, StatusMessage> {
    }

    @UiField
    Label body;

    public StatusMessage(String text) {
	initWidget(uiBinder.createAndBindUi(this));
	body.setText(text);
    }
}
