package com.calclab.hablar.rooms.client.open;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.icons.client.PresenceIcon;

public class SelectRosterItemPresenter {

	private final RosterItem item;
	private final SelectRosterItemDisplay display;

	public SelectRosterItemPresenter(final RosterItem item, final SelectRosterItemDisplay display, final boolean selectable) {
		this.item = item;
		this.display = display;
		display.getName().setText(item.getName());
		display.getJid().setText(item.getJID().toString());
		display.getStatus().setText(item.getStatus());
		display.getSelected().setValue(!selectable);
		display.setSelectEnabled(selectable);
		display.setIcon(PresenceIcon.get(item.isAvailable(), item.getShow()));
	}

	public RosterItem getItem() {
		return item;
	}

	public boolean isSelected() {
		return display.getSelected().getValue();
	}

	public void setEnabled(final boolean enabled) {
		display.setSelectEnabled(enabled);
	}

	public void setSelected(final boolean selected) {
		display.getSelected().setValue(selected);
	}

}
