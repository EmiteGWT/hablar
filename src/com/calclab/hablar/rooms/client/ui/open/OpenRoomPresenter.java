package com.calclab.hablar.rooms.client.ui.open;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public abstract class OpenRoomPresenter extends PagePresenter<OpenRoomDisplay> {

    private final HashMap<XmppURI, SelectRosterItemPresenter> itemsByUri;

    public OpenRoomPresenter(final String type, final HablarEventBus eventBus, final OpenRoomDisplay display) {
	super(type, eventBus, display);
	itemsByUri = new HashMap<XmppURI, SelectRosterItemPresenter>();
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
		requestVisibility(Visibility.notFocused);
	    }
	});
    }

    public Collection<SelectRosterItemPresenter> getItems() {
	return itemsByUri.values();
    }

    public void setItems(final Collection<RosterItem> items, final boolean selectable) {
	display.clearList();
	itemsByUri.clear();
	for (final RosterItem item : items) {
	    createItem(item, selectable, false);
	}
    }

    @Override
    public void setVisibility(final PagePresenter.Visibility visibility) {
	if (visibility == Visibility.focused) {
	    onPageOpen();
	}
	super.setVisibility(visibility);
    }

    protected void createItem(final RosterItem item, final boolean selectable, final boolean selected) {
	final SelectRosterItemDisplay itemDisplay = display.createItem();
	final SelectRosterItemPresenter selectItem = new SelectRosterItemPresenter(item, itemDisplay, selectable);
	selectItem.setSelected(selected);
	display.addItem(itemDisplay);
	itemsByUri.put(item.getJID(), selectItem);
    }

    protected SelectRosterItemPresenter getItem(final XmppURI uri) {
	return itemsByUri.get(uri);
    }

    protected abstract void onAccept();

    protected abstract void onPageOpen();

}
