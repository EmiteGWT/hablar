package com.calclab.hablar.clipboard.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class CopyToClipboardWidget extends Composite implements CopyToClipboardDisplay {

    private static ClipboardMessages messages;

    public static ClipboardMessages i18n() {
	return messages;
    }

    public static void setMessages(final ClipboardMessages messages) {
	CopyToClipboardWidget.messages = messages;
    }

    interface CopyToClipboardWidgetUiBinder extends UiBinder<Widget, CopyToClipboardWidget> {
    }

    private static CopyToClipboardWidgetUiBinder uiBinder = GWT.create(CopyToClipboardWidgetUiBinder.class);

    @UiField
    SpanElement title;
    @UiField
    Button accept, cancel;
    @UiField
    TextArea content;
    @UiField
    LabelElement useYourBrowserLabel;

    public CopyToClipboardWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	accept.ensureDebugId("CopyToClipboardWidget-accept");
	cancel.ensureDebugId("CopyToClipboardWidget-cancel");
	content.ensureDebugId("CopyToClipboardWidget-content");
	title.setInnerText(i18n().copyConversationToClipboard());
	accept.setText(i18n().copyToClipboardAction());
	cancel.setText(i18n().closeAction());
	useYourBrowserLabel.setInnerText(i18n().useYourBrowser());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public Button getCloseButton() {
	return cancel;
    }

    @Override
    public TextArea getContentField() {
	return content;
    }

    @Override
    public Button getCopyButton() {
	return accept;
    }

    @Override
    public void setCopyButtonVisible(final boolean visible) {
	accept.setVisible(visible);
    }

}
