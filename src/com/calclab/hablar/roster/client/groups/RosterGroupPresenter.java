package com.calclab.hablar.roster.client.groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterGroup;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.RosterItemsOrder;
import com.calclab.emite.im.client.roster.events.RosterItemChangedEvent;
import com.calclab.emite.im.client.roster.events.RosterItemChangedHandler;
import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.RosterConfig;
import com.calclab.hablar.roster.client.RosterMessages;

@SuppressWarnings("unchecked")
public class RosterGroupPresenter implements Presenter<RosterGroupDisplay> {

	private final static Comparator<RosterItem> ORDER = RosterItemsOrder.order(
			RosterItemsOrder.byAvailability, RosterItemsOrder.groupedFirst,
			RosterItemsOrder.byName);

	private static RosterMessages messages;

	public static RosterMessages i18n() {
		return messages;
	}

	public static void setMessages(final RosterMessages messages) {
		RosterGroupPresenter.messages = messages;
	}

	private final RosterGroupDisplay display;
	private String groupLabel;
	private final HashMap<XmppURI, RosterItemPresenter> itemPresenters;
	private final ArrayList<RosterItemPresenter> itemPresenterList;
	private final Menu<RosterItemPresenter> itemMenu;

	private final RosterGroup group;

	private final RosterConfig rosterConfig;

	public RosterGroupPresenter(final RosterGroup group,
			final Menu<RosterItemPresenter> itemMenu,
			final RosterGroupDisplay display, final RosterConfig rosterConfig) {
		this.group = group;
		this.itemMenu = itemMenu;
		this.display = display;
		this.rosterConfig = rosterConfig;

		itemPresenters = new HashMap<XmppURI, RosterItemPresenter>();
		itemPresenterList = new ArrayList<RosterItemPresenter>();
		
		display.setVisible(group.isAllContacts());

		group.addRosterItemChangedHandler(new RosterItemChangedHandler() {

			@Override
			public void onRosterItemChanged(RosterItemChangedEvent event) {
				if(event.isAdded() || event.isModified()) {
					createOrModifyItem(event.getRosterItem());
				} else if(event.isRemoved()) {
					removeRosterItem(event.getRosterItem());
				} else {
					refreshRosterItemGroups();
				}
			}
		});
		refreshRosterItemGroups();
	}

	@Override
	public RosterGroupDisplay getDisplay() {
		return display;
	}

	public String getGroupLabel() {
		return groupLabel;
	}

	public String getGroupName() {
		return group.getName();
	}

	public RosterGroup getRosterGroup() {
		return group;
	}

	public boolean isVisible() {
		return display.isVisible();
	}

	public void toggleVisibility() {
		display.setVisible(!display.isVisible());
	}
	
	/**
	 * Called to notify this object that a roster item has been changed
	 * @param item
	 */
	public void rosterItemChanged(final RosterItem item) {
		RosterItemPresenter presenter = removeRosterItem(item);
		
		if(presenter != null) {
			presenter.setItem(item);
			addRosterItemPresenter(presenter);
		}
	}

	/**
	 * Adds an existing {@link RosterItemPresenter} to the display.
	 * @param presenter
	 */
	private void addRosterItemPresenter(final RosterItemPresenter presenter)
	{
		RosterItem item = presenter.getItem();
		RosterItemDisplay itemDisplay = presenter.getDisplay();
		
		String nameOrJid = item.getName();

		if (nameOrJid == null) {
			nameOrJid = item.getJID().getShortName();
		}

		itemPresenters.put(item.getJID(), presenter);
		
		boolean found = false;
		
		for(int i = 0; i < itemPresenterList.size(); ++i) {
			RosterItemPresenter listPresenter = itemPresenterList.get(i);
			
			if(ORDER.compare(listPresenter.getItem(), item) >= 0) {
				itemPresenterList.add(i, presenter);
				display.add(itemDisplay, listPresenter.getDisplay());
				found = true;
				break;
			}
		}
		
		if(!found) {
			display.add(presenter.getDisplay());
			itemPresenterList.add(presenter);
		}
	}
	
	/**
	 * Adds a new {@link RosterItem} to the display (creating widgets and presenters as required)
	 * @param item the roster item.
	 * @return the new presenter.
	 */
	private RosterItemPresenter addRosterItem(final RosterItem item) {
		// FIXME: no mola nada toda esta basura selenium
		final RosterItemDisplay itemDisplay = display.newRosterItemDisplay(
				Idify.id(group.getName()), Idify.id(item.getJID()));

		final RosterItemPresenter presenter = new RosterItemPresenter(
				group.getName(), itemMenu, itemDisplay, rosterConfig);
		
		presenter.setItem(item);
		
		addRosterItemPresenter(presenter);
		
		return presenter;
	}
	
	/**
	 * Will create an item (if it doesn't already exist) or update it if it does.
	 * @param item the roster item.
	 * @return the new or existing presenter.
	 */
	private RosterItemPresenter createOrModifyItem(final RosterItem item)
	{
		RosterItemPresenter presenter = removeRosterItem(item);
		
		if(presenter == null) {
			presenter = addRosterItem(item);
		} else {
			presenter.setItem(item);
			addRosterItemPresenter(presenter);
		}
		return presenter;
	}
	
	/**
	 * Removes the roster item. Does nothing if the item does not exist.
	 * @param item the roster item.
	 * @return the removed presenter (or <code>null</code> if not found)
	 */
	private RosterItemPresenter removeRosterItem(final RosterItem item) {
		RosterItemPresenter presenter = itemPresenters.get(item.getJID());

		if(presenter != null) {
			itemPresenters.remove(presenter);
			itemPresenterList.remove(presenter);
			display.remove(presenter.getDisplay());
		}
		
		return presenter;
	}

	/**
	 * Clears and repopulates the current display.
	 */
	public void refreshRosterItemGroups() {
		if (!itemPresenters.isEmpty()) {
			display.removeAll();
			itemPresenters.clear();
			itemPresenterList.clear();
		}
		final Collection<RosterItem> rosterItems = group.getItemList(ORDER);
		for (final RosterItem item : rosterItems) {
			addRosterItem(item);
		}
	}
}
