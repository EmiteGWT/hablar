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

	public HablarChat(final Hablar hablar, final ChatConfig chatConfig, final XmppRoster roster, final ChatManager chatManager, final StateManager stateManager) {
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
