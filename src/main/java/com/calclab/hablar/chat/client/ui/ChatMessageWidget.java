package com.calclab.hablar.chat.client.ui;

import com.calclab.hablar.chat.client.ui.ChatMessage.MessageType;
import com.calclab.hablar.core.client.validators.Empty;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * A chat message
 */
// FIXME: probably UIBinder is too much
public class ChatMessageWidget extends Composite {
	private static ChatMessageUiBinder uiBinder = GWT.create(ChatMessageUiBinder.class);

	interface ChatMessageUiBinder extends UiBinder<Widget, ChatMessageWidget> {
	}

	@UiField
	SpanElement body, metadata;
	@UiField
	Label author;

	public ChatMessageWidget(final ChatMessage message) {
		initWidget(uiBinder.createAndBindUi(this));
		if (Empty.not(message.metadata)) {
			metadata.setInnerText(message.metadata + " ");
		}
		MessageType type = message.type;
		if (Empty.not(message.author)) {
			author.setText(message.author + ": ");
		} else {
			author.setText("");
			type = ChatMessage.MessageType.info;
		}
		DOM.setStyleAttribute(author.getElement(), "color", message.color);
		final Element parent = body.getParentElement();
		body.removeFromParent();
		final Element newBody = ChatMessageFormatter.format(message.author, message.body);
		parent.appendChild(newBody);
		newBody.addClassName("body");
		newBody.addClassName(type.toString());
		body.addClassName(type.toString());
	}

}
