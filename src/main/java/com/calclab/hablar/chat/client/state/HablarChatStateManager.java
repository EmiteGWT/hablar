package com.calclab.hablar.chat.client.state;

import com.calclab.emite.xep.chatstate.client.ChatStateManager;
import com.calclab.emite.xep.chatstate.client.ChatStateManager.ChatState;
import com.calclab.emite.xep.chatstate.client.StateManager;
import com.calclab.emite.xep.chatstate.client.events.ChatStateNotificationEvent;
import com.calclab.emite.xep.chatstate.client.events.ChatStateNotificationHandler;
import com.calclab.hablar.chat.client.ChatMessages;
import com.calclab.hablar.chat.client.ui.PairChatPage;
import com.google.gwt.user.client.ui.HasText;

public class HablarChatStateManager {

	public static String getStateText(final ChatState state, final String userName) {
		switch (state) {
		case active:
			return ChatMessages.msg.stateActive(userName);
		case composing:
			return ChatMessages.msg.stateComposing(userName);
		case gone:
			return ChatMessages.msg.stateGone(userName);
		case inactive:
			return ChatMessages.msg.stateInactive(userName);
		case pause:
			return ChatMessages.msg.statePause(userName);
		default:
			return state.toString();
		}
	}

	public HablarChatStateManager(final StateManager manager, final PairChatPage page) {
		final ChatStateManager chatState = manager.getChatState(page.getChat());

		chatState.addChatStateNotificationHandler(new ChatStateNotificationHandler() {
			@Override
			public void onChatStateChanged(final ChatStateNotificationEvent event) {
				final ChatState state = event.getChatState();
				final HasText stateDisplay = page.getDisplay().getState();
				stateDisplay.setText(HablarChatStateManager.getStateText(state, page.getChatName()));
			}
		});
	}
}
