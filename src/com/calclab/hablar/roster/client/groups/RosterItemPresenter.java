package com.calclab.hablar.roster.client.groups;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.RosterConfig;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class RosterItemPresenter implements Presenter<RosterItemDisplay> {

    private final RosterItemDisplay display;
    private RosterItem item;
    private final String groupName;
    private String clickActionDescription;

    private String itemName;
    private String itemJid;
    private String itemStatus;
    private boolean itemIsAvailable;
    private Show itemShow;

    public RosterItemPresenter(final String groupName, final Menu<RosterItemPresenter> itemMenu,
	    final RosterItemDisplay display, final RosterConfig rosterConfig) {
	this.groupName = groupName;
	this.display = display;

	this.clickActionDescription = "";
	if (rosterConfig.rosterItemClickAction != null) {
	    this.clickActionDescription = rosterConfig.rosterItemClickAction.getDescription() + " ";
	    display.getAction().addClickHandler(new ClickHandler() {
		@Override
		public void onClick(final ClickEvent event) {
		    rosterConfig.rosterItemClickAction.execute(item);
		}
	    });
	    display.addStyleName("clickable");
	}

	display.getMenuAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		event.stopPropagation();
		event.preventDefault();
		final Element element = event.getRelativeElement();
		itemMenu.setTarget(RosterItemPresenter.this);
		itemMenu.show(element.getAbsoluteLeft(), element.getAbsoluteTop());
	    }
	});

    }

    @Override
    public RosterItemDisplay getDisplay() {
	return display;
    }

    public String getGroupName() {
	return groupName;
    }

    public RosterItem getItem() {
	return item;
    }

    /**
     * Updates the display to reflect the given {@link RosterItem}.
     * 
     * @param item
     *            the item to update the display to.
     * @return <code>true</code> if the item had different data to that already
     *         displayed, <code>false</code> otherwise.
     */
    public boolean setItem(final RosterItem item) {
	boolean hasChanged = false;

	this.item = item;

	String name = item.getName();

	if (name == null) {
	    name = item.getJID().getShortName();
	}

	if (!name.equals(itemName)) {
	    display.getName().setText(name);
	    itemName = name;
	    hasChanged = true;
	}

	final String jidString = item.getJID().toString();

	if (!jidString.equals(itemJid)) {
	    display.getJid().setText(jidString);
	    itemJid = jidString;
	    hasChanged = true;
	}

	final String status = item.getStatus();
	final boolean hasStatus = (status != null) && (status.trim().length() > 0);

	if (hasStatus) {
	    if (!status.equals(itemStatus)) {
		display.getStatus().setText(status);
		itemStatus = status;
		hasChanged = true;
	    }
	}

	display.setStatusVisible(hasStatus);

	if ((item.isAvailable() != itemIsAvailable) || !item.getShow().equals(itemShow)) {
	    display.setIcon(PresenceIcon.get(item.isAvailable(), item.getShow()));
	    itemIsAvailable = item.isAvailable();
	    itemShow = item.getShow();
	    hasChanged = true;
	}

	if (hasChanged) {
	    final String title = clickActionDescription + name + " (" + jidString + ")";
	    display.setWidgetTitle(title);
	    // display.setColor(ColorHelper.getColor(item.getJID()));
	}

	return hasChanged;
    }

}
