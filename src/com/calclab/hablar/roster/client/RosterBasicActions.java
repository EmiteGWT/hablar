package com.calclab.hablar.roster.client;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.suco.client.Suco;

public class RosterBasicActions {

    private static final String ACTION_ID_REMOVE_FROM_ROSTER = "HablarRoster-removeFromRosterAction";
    private static final String ID_ACTION_REMOVE_FROM_GROUP = "HablarRoster-removeFromGroupAction";
    private static final String ID_ACTION_DELETE_GROUP = "HablarRoster-deleteGroupAction";

    private final SimpleAction<RosterItemPresenter> removeFromRoster = new SimpleAction<RosterItemPresenter>(
	    "Remove from roster", ACTION_ID_REMOVE_FROM_ROSTER) {
	@Override
	public void execute(final RosterItemPresenter target) {
	    final RosterItem item = target.getItem();
	    removeFromRoster(item);
	}

	@Override
	public boolean isApplicable(final RosterItemPresenter target) {
	    return target.getGroupName().equals("");
	};
    };

    private final SimpleAction<RosterItemPresenter> removeFromGroup = new SimpleAction<RosterItemPresenter>(
	    "Remove from this group", ID_ACTION_REMOVE_FROM_GROUP) {
	@Override
	public void execute(final RosterItemPresenter target) {
	    removeFromGroup(target.getItem(), target.getGroupName());
	}

	@Override
	public boolean isApplicable(final RosterItemPresenter target) {
	    return !target.getGroupName().equals("");
	};
    };
    private final Action<RosterGroupPresenter> deleteGroup = new SimpleAction<RosterGroupPresenter>("Delete group",
	    ID_ACTION_DELETE_GROUP) {
	@Override
	public void execute(final RosterGroupPresenter target) {
	}

	@Override
	public boolean isApplicable(final RosterGroupPresenter target) {
	    return !target.getGroupName().equals("");
	};
    };

    public RosterBasicActions(final RosterPage rosterPage) {
	rosterPage.getItemMenu().addAction(removeFromRoster);
	rosterPage.getItemMenu().addAction(removeFromGroup);
	rosterPage.getGroupMenu().addAction(deleteGroup);
    }

    protected void removeFromGroup(final RosterItem item, final String groupName) {
	final Roster roster = Suco.get(Roster.class);
	item.removeFromGroup(groupName);
	roster.updateItem(item);
    }

    protected void removeFromRoster(final RosterItem item) {
	final Roster roster = Suco.get(Roster.class);
	roster.removeItem(item.getJID());
    }
}
