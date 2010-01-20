package com.calclab.hablar.signals.client;

import java.util.HashSet;
import java.util.Set;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;

public class ChatSignalsLogic {

    private PageView currentChat;
    private final Event<Set<PageView>> chatUnattended;
    private final Set<PageView> set;

    public ChatSignalsLogic() {
	this.chatUnattended = new Event<Set<PageView>>("chatUnattended");
	set = new HashSet<PageView>();
    }

    public void addChatUnattended(final Listener<Set<PageView>> listener) {
	chatUnattended.add(listener);
    }

    public void onChatClosed(final PageView chatPage) {
	if (set.remove(chatPage)) {
	    fire();
	}
    }

    public void onChatOpened(final PageView chatPage) {
	currentChat = chatPage;
	if (set.remove(chatPage)) {
	    fire();
	}

    }

    public void onNewMsg(final PageView chatPage) {
	if (chatPage != currentChat) {
	    if (set.add(chatPage)) {
		fire();
	    }
	}

    }

    private void fire() {
	chatUnattended.fire(set);
    }

}
