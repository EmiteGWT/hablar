package com.calclab.hablar.chat.client.ui;

import java.util.Date;

import com.calclab.hablar.core.client.validators.Empty;
import com.google.gwt.i18n.client.DateTimeFormat;

public class ChatMessage {

	public static enum MessageType {
		incoming, sent, info, alert
	}

	public String metadata;
	public final String author;
	public final String body;
	public final MessageType type;
	public String color;

	public ChatMessage(final String body) {
		this(null, body, MessageType.info);
	}

	public ChatMessage(final String author, final String body, final MessageType type) {
		this.author = author;
		this.body = body;
		this.type = type;
		this.metadata = null;
		this.color = null;
	}

	@SuppressWarnings("deprecation")
	public void setDate(Date date) {
		final Date now = new Date();
		DateTimeFormat format;
		if (date == null) {
			date = now;
			format = DateTimeFormat.getShortTimeFormat();
		} else if ((date.getYear() == now.getYear()) && (date.getMonth() == now.getMonth()) && (date.getDate() == now.getDate())) {
			format = DateTimeFormat.getShortTimeFormat();
		} else {
			format = DateTimeFormat.getShortDateTimeFormat();
		}

		this.metadata = format.format(date);
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
