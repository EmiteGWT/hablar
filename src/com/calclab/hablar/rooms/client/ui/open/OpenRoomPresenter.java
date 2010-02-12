package com.calclab.hablar.rooms.client.ui.open;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public abstract class OpenRoomPresenter extends PagePresenter<OpenRoomDisplay> {

    private final ArrayList<SelectRosterItemPresenter> selectItems;

    public OpenRoomPresenter(final String type, final HablarEventBus eventBus, final OpenRoomDisplay display) {
	super(type, eventBus, display);
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
		onAccept();
	    }
	});
    }

    public List<SelectRosterItemPresenter> getItems() {
	return selectItems;
    }

    public void setItems(final Collection<RosterItem> items, final boolean selectable) {
	display.clearList();
	selectItems.clear();
	for (final RosterItem item : items) {
	    GWT.log("select roster item");
	    final SelectRosterItemDisplay itemDisplay = display.createItem();
	    final SelectRosterItemPresenter selectItem = new SelectRosterItemPresenter(item, itemDisplay, selectable);
	    display.addItem(itemDisplay);
	    selectItems.add(selectItem);
	}
    }

    @Override
    public void setVisibility(final PagePresenter.Visibility visibility) {
	if (visibility == Visibility.focused) {
	    onPageOpen();
	}
	super.setVisibility(visibility);
    }

    protected abstract void onAccept();

    protected abstract void onPageOpen();

}
