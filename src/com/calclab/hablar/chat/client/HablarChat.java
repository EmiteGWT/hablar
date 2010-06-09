package com.calclab.hablar.chat.client;

import com.calclab.hablar.chat.client.state.HablarChatStateManager;
import com.calclab.hablar.chat.client.ui.PairChatPage;
import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.chat.client.ui.ChatWidget;
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
		if (event.isType(PairChatPresenter.TYPE)) {
		    final PairChatPage page = (PairChatPage) event.getPage();
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
	ChatMessages messages = (ChatMessages) GWT.create(ChatMessages.class);
	setMessages(messages);
	ChatWidget.setMessages(messages);
    }

}
