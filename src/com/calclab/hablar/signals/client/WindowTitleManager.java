package com.calclab.hablar.signals.client;

import java.util.Set;

import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class WindowTitleManager {

    public WindowTitleManager(final ChatSignalsLogic chatSignals) {
	// This can go in another different module (and you can do another kind
	// of title modifications)
	final Msg i18n = Suco.get(Msg.class);
	chatSignals.addChatsUnattendedChanged(new Listener<Set<PageView>>() {
	    @Override
	    public void onEvent(final Set<PageView> set) {
		final int size = set.size();
		WindowTitleHelper.addPrefix(size == 0 ? "" : i18n.unreadChats(size));
	    }
	});
	chatSignals.addNewUnattendedChat(new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView chatPage) {
		// FIXME Here a Grown widget will be great
		// Window.alert(chatPage.getStatusMessage());
	    }
	});
    }
}
