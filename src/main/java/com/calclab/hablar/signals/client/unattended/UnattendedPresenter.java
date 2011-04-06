package com.calclab.hablar.signals.client.unattended;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.signals.client.HablarSignals;
import com.calclab.hablar.signals.client.SignalPreferences;
import com.calclab.hablar.signals.client.WindowTextHelper;
import com.calclab.hablar.signals.client.unattended.UnattendedChatsChangedEvent.ChangeType;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasText;

/**
 * Handles the presentation of unattended chats
 */
public class UnattendedPresenter {
    private static final int BLINK_TIME = 1500;
    private boolean showingMessage;
    private boolean active;
    private final HasText windowDisplay;
    private final UnattendedPagesManager unattendedManager;

    public UnattendedPresenter(final HablarEventBus hablarEventBus, final SignalPreferences preferences,
	    final UnattendedPagesManager unattendedManager, final HasText display) {

	this.unattendedManager = unattendedManager;
	windowDisplay = display;
	active = false;
	showingMessage = false;

	hablarEventBus.addHandler(UnattendedChatsChangedEvent.TYPE, new UnattendedChatsChangedHandler() {
	    @Override
	    public void handleUnattendedChatChange(final UnattendedChatsChangedEvent event) {
		if (preferences.titleSignals) {
		    final int unattendedChatsCount = unattendedManager.getSize();
		    
		    if (unattendedChatsCount > 0 && active == false) {
			startTitleChange();
		    } else if (unattendedChatsCount == 0 && active == true) {
			stopTitleChange();
		    }
		}
		changePageStatus(event);
	    }

	});
    }

    private void showUnattendedInTitle(final int size) {
	final String message = size == 0 ? "" : HablarSignals.signalMessages.unreadChats(size);
	final String oldTitle = windowDisplay.getText();
	final String newTitle = WindowTextHelper.updateTitle(oldTitle, message);
	windowDisplay.setText(newTitle);
    }

    private void startTitleChange() {
	active = true;
	showingMessage = false;
	toggleWindowTitle();

    }

    private void toggleWindowTitle() {
	if (active) {
	    if (showingMessage == false) {
		showingMessage = true;
		showUnattendedInTitle(unattendedManager.getSize());
	    } else {
		showingMessage = false;
		showUnattendedInTitle(0);
	    }
	    new Timer() {
		@Override
		public void run() {
		    toggleWindowTitle();
		}
	    }.schedule(BLINK_TIME);
	}
    }

    protected void changePageStatus(final UnattendedChatsChangedEvent event) {
	final Page<?> page = event.getPage();
	final ChangeType type = event.getChangeType();
	final String externalState = type == ChangeType.added ? "unattended" : null;

	page.getState().setExternalState(externalState);
	if (type == ChangeType.added) {
	    togglePageBlink(page, true);
	}
    }

    protected void togglePageBlink(final Page<?> page, final boolean on) {
	// TODO This is non-ideal. Need to come up with a better way of doing this.

	if((page.getState() == null) || (page.getState().getExternalState() == null))
	    return;
	
	if (page.getState().getExternalState().startsWith("unattended")) {
	    if (on) {
		page.getState().setExternalState("unattended blinkOn");
	    } else {
		page.getState().setExternalState("unattended");
	    }

	    new Timer() {
		public void run() {
		    togglePageBlink(page, !on);
		}
	    }.schedule(BLINK_TIME);
	}
    }

    protected void stopTitleChange() {
	active = false;
	showUnattendedInTitle(0);
    }

}
