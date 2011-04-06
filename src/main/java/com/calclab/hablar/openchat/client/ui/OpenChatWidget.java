package com.calclab.hablar.openchat.client.ui;

import com.calclab.hablar.core.client.validators.HasState;
import com.calclab.hablar.openchat.client.OpenChatMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OpenChatWidget extends Composite implements OpenChatDisplay {

    private static OpenChatMessages messages;

    public static void setMessages(final OpenChatMessages messages) {
	OpenChatWidget.messages = messages;
    }

    public static OpenChatMessages i18n() {
	return messages;
    }

    interface OpenChatWidgetUiBinder extends UiBinder<Widget, OpenChatWidget> {
    }

    private static OpenChatWidgetUiBinder uiBinder = GWT.create(OpenChatWidgetUiBinder.class);

    @UiField
    Button accept, cancel;
    @UiField
    TextBox jabberId;
    @UiField
    CheckBox addToRoster;
    @UiField
    LabelElement jabberIdLabel;
    @UiField
    Label jabberIdError;

    @UiField
    SpanElement title;

    public OpenChatWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	accept.ensureDebugId("OpenChatWidget-open");
	cancel.ensureDebugId("OpenChatWidget-cancel");
	jabberId.ensureDebugId("OpenChatWidget-jabberId");
	addToRoster.ensureDebugId("OpenChatWidget-addToRoster");
	title.setInnerText(i18n().openNewChatLabelText());
	jabberIdLabel.setInnerText(i18n().jabberIdLabelText());
	addToRoster.setText(i18n().addToRosterLabelText());
	accept.setText(i18n().openChatAction());
	cancel.setText(i18n().cancelAction());
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
		accept.setEnabled(state);
	    }
	};
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
	return jabberId;
    }

    @Override
    public HasText getJabberIdError() {
	return jabberIdError;
    }

    @Override
    public HasKeyDownHandlers getNameKeys() {
	return jabberId;
    }

    @Override
    public HasClickHandlers getOpen() {
	return accept;
    }

}
