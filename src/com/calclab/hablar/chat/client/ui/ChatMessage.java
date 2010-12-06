package com.calclab.hablar.chat.client.ui;

import com.calclab.hablar.core.client.validators.Empty;

public class ChatMessage {

    public static enum MessageType {
	incoming, sent, info, alert
    }
    public String metadata;
    public String author;
    public String body;
    public MessageType type;
    public final String color;

    public ChatMessage(final String body) {
	this(null, null, ColorHelper.ME, body, MessageType.info);
    }

    public ChatMessage(final String metadata, final String color, final String author, final String body,
	    final MessageType type) {
	this.metadata = metadata;
	this.author = author;
	this.color = color;
	this.body = body;
	this.type = type;
    }

    @Override
    public String toString() {
	return toString(new StringBuilder());
    }

    public String toString(final StringBuilder bf) {
	if (Empty.not(metadata)) {
	    bf.append(metadata).append(" ");
	}
	if (Empty.not(author)) {
	    bf.append(author).append(": ");
	}
	if (Empty.not(body)) {
	    bf.append(body);
	}
	return bf.toString();

    }
}
