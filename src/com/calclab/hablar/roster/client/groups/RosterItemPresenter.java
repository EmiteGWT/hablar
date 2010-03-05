package com.calclab.hablar.roster.client.groups;

import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.suco.client.Suco;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class RosterItemPresenter implements Presenter<RosterItemDisplay> {

    private final RosterItemDisplay display;
    private RosterItem item;
    private final String groupName;

    public RosterItemPresenter(final String groupName, final Menu<RosterItemPresenter> itemMenu,
	    final RosterItemDisplay display) {
	this.groupName = groupName;
	this.display = display;

	final ChatManager manager = Suco.get(ChatManager.class);

	display.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		manager.open(item.getJID());
	    }
	});
	display.getMenuAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
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

    public void setItem(final RosterItem item) {
	this.item = item;
	display.getName().setText(item.getName());
	display.getJid().setText(item.getJID().toString());
	final String status = item.getStatus();
	final boolean hasStatus = status != null && status.trim().length() > 0;
	if (hasStatus) {
	    display.getStatus().setText(status);
	}
	display.setStatusVisible(hasStatus);
	display.setIcon(PresenceIcon.getIcon(item.isAvailable(), item.getShow()));
    }

}
