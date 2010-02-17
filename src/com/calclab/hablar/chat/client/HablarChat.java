package com.calclab.hablar.chat.client;

import com.calclab.hablar.chat.client.state.HablarChatStateManager;
import com.calclab.hablar.chat.client.ui.ChatPage;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
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

    public static void install(final Hablar hablar, final ChatConfig config) {
	new HablarChatManager(hablar, config);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		if (event.isType(ChatPresenter.TYPE)) {
		    final ChatPage page = (ChatPage) event.getPage();
		    new HablarChatStateManager(page);
		}
	    }
	}, true);
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
