package com.calclab.hablar.openchat.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OpenChatWidget extends Composite implements OpenChatDisplay {

    interface OpenChatWidgetUiBinder extends UiBinder<Widget, OpenChatWidget> {
    }

    private static OpenChatWidgetUiBinder uiBinder = GWT.create(OpenChatWidgetUiBinder.class);

    @UiField
    Button open, cancel;
    @UiField
    TextBox jabberID;
    @UiField
    CheckBox addToRoster;

    public OpenChatWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	open.ensureDebugId("OpenChatWidget-open");
	cancel.ensureDebugId("OpenChatWidget-cancel");
	jabberID.ensureDebugId("OpenChatWidget-jabberId");
	addToRoster.ensureDebugId("OpenChatWidget-addToRoster");
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasValue<Boolean> getAddToRoster() {
	return addToRoster;
    }

    @Override
    public HasClickHandlers getCancel() {
	return cancel;
    }

    @Override
    public HasText getJabberId() {
	return jabberID;
    }

    @Override
    public HasClickHandlers getOpen() {
	return open;
    }

}
