package com.calclab.hablar.roster.client;

import java.util.Collection;

import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.core.client.ui.prompt.ConfirmPage;
import com.calclab.hablar.core.client.ui.prompt.UserConfirmationHandler;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;

public class RosterBasicActions {

    protected static final String ACTION_ID_START_CHAT = "HablarRoster-startChat";
    protected static final String ACTION_ID_REMOVE_FROM_ROSTER = "HablarRoster-removeFromRosterAction";
    protected static final String ID_ACTION_REMOVE_FROM_GROUP = "HablarRoster-removeFromGroupAction";
    protected static final String ID_ACTION_DELETE_GROUP = "HablarRoster-deleteGroupAction";

    private static RosterMessages messages;

    public static RosterMessages i18n() {
	return messages;
    }

    public static void setMessages(final RosterMessages messages) {
	RosterBasicActions.messages = messages;
    }

    private final XmppRoster roster;
    private final HablarEventBus eventBus;
    private Action<RosterItem> rosterClickAction;
    private final ChatManager chatManager;

    public RosterBasicActions(final XmppRoster roster, final ChatManager chatManager, final HablarEventBus eventBus) {
	this.roster = roster;
	this.chatManager = chatManager;
	this.eventBus = eventBus;
    }

    public void addHighPriorityActions(final RosterPage rosterPage) {
	rosterPage.getItemMenu().addAction(newStartChatAction());
    }

    public void addLowPriorityActions(final RosterPage rosterPage) {
	rosterPage.getItemMenu().addAction(newRemoveFromRosterAction());
	rosterPage.getItemMenu().addAction(newRemoveFromGroupAction());
	rosterPage.getGroupMenu().addAction(newDeleteGroupAction());
    }

    private void deleteGroup(final String groupName, final Collection<RosterItem> items) {
	for (final RosterItem item : items) {
	    item.removeFromGroup(groupName);
	}
	roster.requestUpdateItems(items);
    }

    public Action<RosterItem> getRosterClickAction() {
	return rosterClickAction;
    }

    private boolean isEntrieRoster(final RosterItemPresenter target) {
	return target.getGroupName() == null;
    }

    private Action<RosterGroupPresenter> newDeleteGroupAction() {
	return new SimpleAction<RosterGroupPresenter>(i18n().deleteGroupAction(), ID_ACTION_DELETE_GROUP) {
	    @Override
	    public void execute(final RosterGroupPresenter target) {

		final String groupName = target.getGroupName();
		final Collection<RosterItem> items = roster.getItemsByGroup(groupName);

		final String title = i18n().confirmDeleteGroupTitle(groupName);
		final String message = i18n().confirmDeleteGroup(groupName, "" + items.size());
		ConfirmPage.show(eventBus, title, message, new UserConfirmationHandler() {
		    @Override
		    public void accept() {
			deleteGroup(groupName, items);
		    }

		    @Override
		    public void cancel() {
		    }
		});
	    }

	    @Override
	    public boolean isApplicable(final RosterGroupPresenter target) {
		return target.getGroupName() != null;
	    }
	};
    }

    private Action<RosterItemPresenter> newRemoveFromGroupAction() {
	return new SimpleAction<RosterItemPresenter>(i18n().removeFromGroupAction(), ID_ACTION_REMOVE_FROM_GROUP) {
	    @Override
	    public void execute(final RosterItemPresenter target) {
		removeFromGroup(target.getItem(), target.getGroupName());
	    }

	    @Override
	    public boolean isApplicable(final RosterItemPresenter target) {
		return !isEntrieRoster(target);
	    }
	};
    }

    private Action<RosterItemPresenter> newRemoveFromRosterAction() {
	return new SimpleAction<RosterItemPresenter>(i18n().removeContactAction(), ACTION_ID_REMOVE_FROM_ROSTER) {
	    @Override
	    public void execute(final RosterItemPresenter target) {
		final RosterItem item = target.getItem();
		roster.requestRemoveItem(item.getJID());
	    }

	    @Override
	    public boolean isApplicable(final RosterItemPresenter target) {
		return isEntrieRoster(target);
	    }
	};
    }

    private Action<RosterItemPresenter> newStartChatAction() {
	return new SimpleAction<RosterItemPresenter>(i18n().startChatAction(), ACTION_ID_START_CHAT) {
	    @Override
	    public void execute(final RosterItemPresenter target) {
		chatManager.open(target.getItem().getJID());
	    }
	};
    }

    private void removeFromGroup(final RosterItem item, final String groupName) {
	item.removeFromGroup(groupName);
	roster.requestUpdateItem(item);
    }

}
