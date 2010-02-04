package com.calclab.hablar.roster.client;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;

public class RosterItemPresenter implements Presenter<RosterItemDisplay> {

    private final RosterItemDisplay display;

    public RosterItemPresenter(RosterItemDisplay display) {
	this.display = display;
    }

    @Override
    public RosterItemDisplay getDisplay() {
	return display;
    }

    public void setItem(RosterItem item) {
	display.getName().setText(item.getName());
	display.getJid().setText(item.getJID().toString());
	final String status = item.getStatus();
	boolean hasStatus = status != null && status.trim().length() > 0;
	if (hasStatus) {
	    display.getStatus().setText(status);
	}
	display.setStatusVisible(hasStatus);
	display.setIcon(PresenceIcon.getIcon(item.isAvailable(), item.getShow()));
    }

}
