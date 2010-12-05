package com.calclab.hablar.usergroups.client;

import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.usergroups.client.changegroups.ManageGroupsPresenter;
import com.calclab.hablar.usergroups.client.changegroups.ManageGroupsWidget;
import com.calclab.suco.client.Suco;

public class HablarUserGroups {
    private static final String ACTION_ID_ADD_TO_GROUP = "HablarRoster-manageGroupsAction";

    private static UserGroupsMessages messages;

    public static UserGroupsMessages i18n() {
	return messages;
    }

    public static void setMessages(final UserGroupsMessages messages) {
	HablarUserGroups.messages = messages;
    }

    // FIXME: move to gin
    // FIXME: resoterPage can't be a parameter when move to gin
    @SuppressWarnings("deprecation")
    public HablarUserGroups(final RosterPage rosterPage, final Hablar hablar) {
	final XmppRoster roster = Suco.get(XmppRoster.class);

	final ManageGroupsPresenter movePage = new ManageGroupsPresenter(roster, hablar.getEventBus(),
		new ManageGroupsWidget());
	hablar.addPage(movePage, OverlayContainer.ROL);

	final String title = i18n().changeContactGroups();
	final String icon = Icons.ROSTER;
	final SimpleAction<RosterItemPresenter> addToGroup = new SimpleAction<RosterItemPresenter>(title,
		ACTION_ID_ADD_TO_GROUP, icon) {
	    @Override
	    public void execute(final RosterItemPresenter target) {
		movePage.setItem(target.getItem());
		movePage.requestVisibility(Visibility.focused);
	    }

	    @Override
	    public boolean isApplicable(final RosterItemPresenter target) {
		return target.getGroupName() == null;
	    }
	};
	rosterPage.getItemMenu().addAction(addToGroup);
    }

}
