package com.calclab.hablar.chat.client.ui;

import com.calclab.emite.core.client.packet.TextUtils;

/**
 * Some formatting utilities
 */
public class ChatMessageFormatter {

    public static String ellipsis(final String text, final int length) {
	return text.length() > length ? text.substring(0, length - 3) + "..." : text;
    }

    public static String format(final String message) {
	if (message != null) {
	    String formatted = TextUtils.escape(message);
	    formatted = formatted.replaceAll("\n", "<br>\n");
	    formatted = formatUrls(formatted);
	    return formatted;
	} else {
	    return null;
	}
    }

    private static String formatUrls(String message) {
	return message = message.replaceAll(TextUtils.URL_REGEXP, "<a href=\"$1\" target=\"_blank\">$1</a>");
    }

    private ChatMessageFormatter() {
    }

}
