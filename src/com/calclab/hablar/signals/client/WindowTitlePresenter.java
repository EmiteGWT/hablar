package com.calclab.hablar.signals.client;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.signals.client.unattended.UnattendedChatsChangedEvent;
import com.calclab.hablar.signals.client.unattended.UnattendedMessageHandler;
import com.google.gwt.user.client.ui.HasText;

public class WindowTitlePresenter {
    public WindowTitlePresenter(final HablarEventBus hablarEventBus, final SignalPreferences preferences,
	    final HasText display) {

	hablarEventBus.addHandler(UnattendedChatsChangedEvent.TYPE, new UnattendedMessageHandler() {
	    @Override
	    public void handleUnattendedMessage(final UnattendedChatsChangedEvent event) {
		if (preferences.titleSignals) {
		    final int size = event.getUnattendedChatPages().getSize();
		    final String message = size == 0 ? "" : I18nSignals.t.unreadChats(size);
		    final String oldTitle = display.getText();
		    final String newTitle = WindowTextHelper.updateTitle(oldTitle, message);
		    display.setText(newTitle);
		}
	    }
	});
    }

}
