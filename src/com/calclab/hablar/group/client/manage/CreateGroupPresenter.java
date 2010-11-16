package com.calclab.hablar.group.client.manage;

import java.util.Collection;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Displays a form to manage the creation of a group in roster.
 */
public class CreateGroupPresenter extends ManageGroupPresenter {

    public CreateGroupPresenter(HablarEventBus eventBus, final ManageGroupDisplay display, String pageTitle) {
	super(eventBus, display, pageTitle);
	display.getApply().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		String groupName = display.getGroupNameText();
		Collection<RosterItem> items = display.getSelectedItems();
		XmppRoster roster = Suco.get(XmppRoster.class);
		for (RosterItem item : items) {
		    item.addToGroup(groupName);
		}
		roster.requestUpdateItems(items);
		requestVisibility(Visibility.hidden);
	    }
	});
    }

    protected void preloadForm() {
	display.clearSelectionList();
	XmppRoster roster = Suco.get(XmppRoster.class);
	for (RosterItem item: roster.getItems()) {
	    display.addRosterItem(item);
	}
    }
}
