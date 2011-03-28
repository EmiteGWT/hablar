package com.calclab.hablar.group.client.manage;

import java.util.Collection;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Displays a form to manage the creation of a group in roster.
 */
public class CreateGroupPresenter extends ManageGroupPresenter {

    private final XmppRoster roster;
    private final XmppSession session;

    public CreateGroupPresenter(final XmppSession session, final XmppRoster roster, final HablarEventBus eventBus,
	    final ManageGroupDisplay display, final String pageTitle) {
	super(eventBus, display, pageTitle);
	this.session = session;
	this.roster = roster;
	display.getApply().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final String groupName = display.getGroupNameText();
		final Collection<RosterItem> items = display.getSelectedItems();
		for (final RosterItem item : items) {
		    item.addToGroup(groupName);
		}
		roster.requestUpdateItems(items);
		requestVisibility(Visibility.hidden);
	    }
	});
    }

    @Override
    protected void preloadForm() {
	final XmppURI currentUser = session.getCurrentUserURI();
	display.clearSelectionList();
	for (final RosterItem item : roster.getItems()) {
	    if (!currentUser.equalsNoResource(item.getJID())) {
		display.addRosterItem(item);
	    }
	}
    }
}
