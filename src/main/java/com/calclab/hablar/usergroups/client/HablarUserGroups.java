package com.calclab.hablar.usergroups.client;

import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.icons.client.IconsBundle;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.usergroups.client.changegroups.ManageGroupsPresenter;
import com.calclab.hablar.usergroups.client.changegroups.ManageGroupsWidget;

public class HablarUserGroups {
	private static final String ACTION_ID_ADD_TO_GROUP = "HablarRoster-manageGroupsAction";

	public HablarUserGroups(final RosterPage rosterPage, final Hablar hablar, final XmppRoster roster) {

		final ManageGroupsPresenter movePage = new ManageGroupsPresenter(roster, hablar.getEventBus(), new ManageGroupsWidget());
		hablar.addPage(movePage, OverlayContainer.ROL);

		final String title = UserGroupsMessages.msg.changeContactGroups();
		final SimpleAction<RosterItemPresenter> addToGroup = new SimpleAction<RosterItemPresenter>(title, ACTION_ID_ADD_TO_GROUP, IconsBundle.bundle.rosterIcon()) {
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
