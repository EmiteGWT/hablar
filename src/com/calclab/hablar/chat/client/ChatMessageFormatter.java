package com.calclab.hablar.chat.client;

import com.calclab.emite.core.client.packet.TextUtils;

public class ChatMessageFormatter {

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

    protected ChatMessageFormatter() {
    }

}
