package com.calclab.hablar.chat.client.ui;

import com.calclab.hablar.core.client.validators.IsEmpty;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A chat message
 */
// FIXME: probably UIBinder is too much
public class ChatMessageWidget extends Composite {

    interface ChatMessageUiBinder extends UiBinder<Widget, ChatMessageWidget> {
    }

    private static ChatMessageUiBinder uiBinder = GWT.create(ChatMessageUiBinder.class);

    @UiField
    SpanElement author, body, metadata;

    public ChatMessageWidget(final ChatMessage message) {
	initWidget(uiBinder.createAndBindUi(this));
	if (IsEmpty.not(message.metadata)) {
	    metadata.setInnerText(message.metadata);
	}
	if (IsEmpty.not(message.author)) {
	    author.setInnerText(message.author + ": ");
	} else {
	    message.type = ChatMessage.MessageType.info;
	}
	final Element parent = body.getParentElement();
	body.removeFromParent();
	final Element newBody = ChatMessageFormatter.format(message.author, message.body);
	parent.appendChild(newBody);
	newBody.addClassName("body");
	newBody.addClassName(message.type.toString());
	body.addClassName(message.type.toString());
    }

}
