package com.calclab.hablar.signals.client;

import static com.calclab.hablar.core.client.i18n.Translator.i18n;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.google.gwt.user.client.ui.HasText;

public class WindowTitlePresenter {
    public WindowTitlePresenter(HablarEventBus hablarEventBus, final HasText display) {

	hablarEventBus.addHandler(UnattendedChatsChangedEvent.TYPE, new UnattendedMessageHandler() {
	    @Override
	    public void handleUnattendedMessage(UnattendedChatsChangedEvent event) {
		final int size = event.getUnattendedChatPages().getSize();
		String message = size == 0 ? "" : i18n().unreadChats(size);
		String oldTitle = display.getText();
		String newTitle = WindowTextHelper.updateTitle(oldTitle, message);
		display.setText(newTitle);
	    }
	});

    }

}
