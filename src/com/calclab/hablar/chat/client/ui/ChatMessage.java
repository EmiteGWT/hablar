package com.calclab.hablar.chat.client.ui;

import com.calclab.hablar.core.client.validators.IsEmpty;

public class ChatMessage {

    public static enum MessageType {
	incoming, sent, info, alert
    }
    public String metadata;
    public String author;
    public String body;
    public MessageType type;

    public ChatMessage(final String body) {
	this(null, null, body, MessageType.info);
    }

    public ChatMessage(final String metadata, final String author, final String body, final MessageType type) {
	this.metadata = metadata;
	this.author = author;
	this.body = body;
	this.type = type;
    }

    @Override
    public String toString() {
	final StringBuilder bf = new StringBuilder();
	if (IsEmpty.not(metadata)) {
	    bf.append(metadata).append(" ");
	}
	if (IsEmpty.not(author)) {
	    bf.append(author).append(": ");
	}
	if (IsEmpty.not(body)) {
	    bf.append(body);
	}
	return bf.toString();
    }
}
