package com.calclab.hablar.chat.client.state;

import static com.calclab.hablar.chat.client.HablarChat.i18n;

import com.calclab.emite.xep.chatstate.client.ChatStateManager;
import com.calclab.emite.xep.chatstate.client.StateManager;
import com.calclab.emite.xep.chatstate.client.ChatStateManager.ChatState;
import com.calclab.hablar.chat.client.ui.ChatPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.user.client.ui.HasText;

public class HablarChatStateManager {

    public static String getStateText(final ChatState state, final String userName) {
	switch (state) {
	case active:
	    return i18n().stateActive(userName);
	case composing:
	    return i18n().stateComposing(userName);
	case gone:
	    return i18n().stateGone(userName);
	case inactive:
	    return i18n().stateInactive(userName);
	case pause:
	    return i18n().statePause(userName);
	default:
	    return state.toString();
	}
    }

    public HablarChatStateManager(final ChatPage page) {
	final StateManager manager = Suco.get(StateManager.class);
	final ChatStateManager chatState = manager.getChatState(page.getChat());
	chatState.onChatStateChanged(new Listener<ChatState>() {
	    @Override
	    public void onEvent(final ChatState state) {
		final HasText stateDisplay = page.getDisplay().getState();
		stateDisplay.setText(HablarChatStateManager.getStateText(state, page.getChatName()));
	    }
	});
    }
}
