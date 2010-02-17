package com.calclab.hablar.chat.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarChat implements EntryPoint {

    private static ChatMessages chatMessages;

    public static ChatMessages i18n() {
	return chatMessages;
    }

    public static void install(final Hablar hablar) {
	install(hablar, ChatConfig.getFromMeta());
    }

    public static void install(final Hablar hablarPresenter, final ChatConfig config) {
	new HablarChatManager(hablarPresenter, config);
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar(), ChatConfig.getFromMeta());
    }

    public static void setMessages(final ChatMessages messages) {
	chatMessages = messages;
    }

    @Override
    public void onModuleLoad() {
	setMessages((ChatMessages) GWT.create(ChatMessages.class));
    }

}
