package com.calclab.hablar.chat.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A chat message
 */
// FIXME: probably UIBinder is too much
public class ChatMessage extends Composite {

    interface ChatMessageUiBinder extends UiBinder<Widget, ChatMessage> {
    }

    private static ChatMessageUiBinder uiBinder = GWT.create(ChatMessageUiBinder.class);

    @UiField
    SpanElement author;
    @UiField
    SpanElement body;

    public ChatMessage(final String name, final String body, ChatDisplay.MessageType type) {
	initWidget(uiBinder.createAndBindUi(this));
	if (name != null && name.length() > 0) {
	    author.setInnerText(name + ": ");
	} else {
	    type = ChatDisplay.MessageType.info;
	}
	this.body.setInnerHTML(body);
	this.body.addClassName(type.toString());
    }

}
