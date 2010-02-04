package com.calclab.hablar.core.client.i18n;

public class Translator {

    private static HablarMessages messages;

    public static HablarMessages i18n() {
	return Translator.messages;
    }

    public static void setMessages(HablarMessages messages) {
	Translator.messages = messages;
    }
}
