package com.calclab.hablar.signals.client;

import java.util.Set;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.suco.client.events.Listener;

public class WindowTitleManager {

    public WindowTitleManager(final ChatSignalsLogic chatSignals) {
	// This can go in another different module
	chatSignals.addChatUnattended(new Listener<Set<PageView>>() {
	    @Override
	    public void onEvent(final Set<PageView> set) {
		final int size = set.size();
		WindowTitleHelper.addPrefix(size == 0 ? "" : String.valueOf(size));
	    }
	});
    }
}
