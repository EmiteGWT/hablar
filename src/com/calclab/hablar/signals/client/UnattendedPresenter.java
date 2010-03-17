package com.calclab.hablar.signals.client;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.signals.client.unattended.UnattendedMessageHandler;
import com.calclab.hablar.signals.client.unattended.UnattendedPagesChangedEvent;
import com.calclab.hablar.signals.client.unattended.UnattendedPagesManager;
import com.calclab.hablar.signals.client.unattended.UnattendedPagesChangedEvent.ChangeType;
import com.google.gwt.user.client.ui.HasText;

public class UnattendedPresenter {
    public UnattendedPresenter(final HablarEventBus hablarEventBus, final SignalPreferences preferences,
	    final UnattendedPagesManager unattendedManager, final HasText display) {

	hablarEventBus.addHandler(UnattendedPagesChangedEvent.TYPE, new UnattendedMessageHandler() {
	    @Override
	    public void handleUnattendedMessage(final UnattendedPagesChangedEvent event) {
		if (preferences.titleSignals) {
		    changeTitle(display, event);
		}
		changePageStatus(event);
	    }

	    private void changeTitle(final HasText display, final UnattendedPagesChangedEvent event) {
		final int size = unattendedManager.getSize();
		final String message = size == 0 ? "" : I18nSignals.t.unreadChats(size);
		final String oldTitle = display.getText();
		final String newTitle = WindowTextHelper.updateTitle(oldTitle, message);
		display.setText(newTitle);
	    }
	});
    }

    protected void changePageStatus(final UnattendedPagesChangedEvent event) {
	final Page<?> page = event.getPage();
	final ChangeType type = event.getChangeType();
	final String externalState = type == ChangeType.added ? "unattended" : null;
	page.getState().setExternalState(externalState);
    }

}
