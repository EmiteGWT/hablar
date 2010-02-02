package com.calclab.hablar.chat.client.ui;

import com.calclab.emite.core.client.packet.TextUtils;

/**
 * Some formatting utilities
 */
public class ChatMessageFormatter {

    public static String ellipsis(final String text, final int length) {
	return text.length() > length ? text.substring(0, length - 3) + "..." : text;
    }

    public static String format(final String messageOrig) {
	String message = messageOrig;
	message = TextUtils.escape(message);
	message = message.replaceAll("\n", "<br>\n");
	message = formatUrls(message);
	return message;
    }

    private static String formatUrls(String message) {
	return message = message.replaceAll(TextUtils.URL_REGEXP, "<a href=\"$1\" target=\"_blank\">$1</a>");
    }

    private ChatMessageFormatter() {
    }

}
