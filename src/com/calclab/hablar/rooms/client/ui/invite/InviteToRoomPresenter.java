package com.calclab.hablar.rooms.client.ui.invite;

import java.util.ArrayList;
import java.util.Collection;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class InviteToRoomPresenter extends PagePresenter<InviteToRoomDisplay> {
    private static final String TYPE = "InviteToRoom";
    private final ArrayList<SelectRosterItemPresenter> selectItems;
    private Room room;

    public InviteToRoomPresenter(final HablarEventBus eventBus, final InviteToRoomDisplay display) {
	super(TYPE, eventBus, display);
	selectItems = new ArrayList<SelectRosterItemPresenter>();
	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getInvite().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		invitePeople();
	    }
	});
    }

    public void setRoom(final Room room) {
	this.room = room;
    }

    @Override
    public void setVisibility(final com.calclab.hablar.core.client.page.PagePresenter.Visibility visibility) {
	if (visibility == Visibility.focused) {
	    loadRosterItems();
	}
	super.setVisibility(visibility);
    }

    private void loadRosterItems() {
	GWT.log("LOAD ITEMS");
	final Roster roster = Suco.get(Roster.class);
	final Collection<RosterItem> items = roster.getItems();
	display.clearList();
	selectItems.clear();
	for (final RosterItem item : items) {
	    GWT.log("select roster item");
	    final SelectRosterItemDisplay itemDisplay = display.createItem();
	    final SelectRosterItemPresenter selectItem = new SelectRosterItemPresenter(item, itemDisplay);
	    display.addItem(itemDisplay);
	    selectItems.add(selectItem);
	}
    }

    protected void invitePeople() {
	final String reasonText = display.getMessage().getText();
	for (final SelectRosterItemPresenter selectItem : selectItems) {
	    if (selectItem.isSelected()) {
		room.sendInvitationTo(selectItem.getItem().getJID(), reasonText);
	    }
	}
	requestVisibility(Visibility.hidden);
    }

}
