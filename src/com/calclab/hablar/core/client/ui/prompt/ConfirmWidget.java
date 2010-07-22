package com.calclab.hablar.core.client.ui.prompt;

import com.calclab.hablar.core.client.CoreMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ConfirmWidget extends Composite implements ConfirmPageDisplay {

    private static ConfirmWidgetUiBinder uiBinder = GWT.create(ConfirmWidgetUiBinder.class);

    private static CoreMessages messages;

    public static void setMessages(final CoreMessages messages) {
	ConfirmWidget.messages = messages;
    }

    public static CoreMessages i18n() {
	return messages;
    }

    interface ConfirmWidgetUiBinder extends UiBinder<Widget, ConfirmWidget> {
    }

    @UiField
    SpanElement title;
    @UiField
    Label message;
    @UiField
    Button yes, no;

    public ConfirmWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	yes.setText(i18n().yes());
	no.setText(i18n().no());
    }

    @Override
    public void setPageTitle(String titleMessage) {
	if (titleMessage != null) {
	    title.setInnerText(titleMessage);
	} else {
	    title.setInnerText("");
	}

    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getYes() {
	return yes;
    }

    @Override
    public HasClickHandlers getNo() {
	return no;
    }

    @Override
    public HasText getMessage() {
	return message;
    }

}
