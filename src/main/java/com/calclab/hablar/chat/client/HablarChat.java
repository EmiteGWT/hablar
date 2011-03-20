package com.calclab.hablar.chat.client;

import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.chatstate.client.StateManager;
import com.calclab.hablar.chat.client.state.HablarChatStateManager;
import com.calclab.hablar.chat.client.ui.PairChatPage;
import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;

public class HablarChat {

    private static ChatMessages chatMessages;

    public static ChatMessages i18n() {
	return chatMessages;
    }

    public static void setMessages(final ChatMessages messages) {
	chatMessages = messages;
    }

    public HablarChat(final Hablar hablar, final ChatConfig chatConfig, final XmppRoster roster,
	    final ChatManager chatManager, final StateManager stateManager) {
	new HablarChatManager(roster, chatManager, hablar, chatConfig);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		if (event.isType(PairChatPresenter.TYPE)) {
		    final PairChatPage page = (PairChatPage) event.getPage();
		    new HablarChatStateManager(stateManager, page);
		}
	    }
	}, true);
    }

}
