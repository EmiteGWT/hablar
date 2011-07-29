package com.calclab.hablar.chat.client;

import org.openqa.jetty.jetty.servlet.Default;

import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.chatstate.client.StateManager;
import com.calclab.hablar.chat.client.state.HablarChatStateManager;
import com.calclab.hablar.chat.client.ui.PairChatPage;
import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.chat.client.ui.chatmessageformat.ChatMessageFormatter;
import com.calclab.hablar.chat.client.ui.chatmessageformat.StandardChatMessageFormatReplacements;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.ui.emoticons.EmoticonsChatMessageFormatReplacements;
import com.calclab.suco.client.Suco;
import com.google.inject.Inject;

public class HablarChat {

    private static ChatMessages chatMessages;

    public static ChatMessages i18n() {
	return chatMessages;
    }

    public static void setMessages(final ChatMessages messages) {
	chatMessages = messages;
    }

    // FIXME: add suco
    @SuppressWarnings("deprecation")
    @Inject
    public HablarChat(final Hablar hablar, final ChatConfig chatConfig) {
	final XmppRoster roster = Suco.get(XmppRoster.class);
	final ChatManager chatManager = Suco.get(ChatManager.class);
	final StateManager stateManager = Suco.get(StateManager.class);

	new HablarChatManager(roster, chatManager, hablar, chatConfig);

	// Set up the message replacements
	ChatMessageFormatter.addReplacements(new StandardChatMessageFormatReplacements());
	
	if(chatConfig.enableEmoticons) {
		ChatMessageFormatter.addReplacements(new EmoticonsChatMessageFormatReplacements());
	}
	
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
