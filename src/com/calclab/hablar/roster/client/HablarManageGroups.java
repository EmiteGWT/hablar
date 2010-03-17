package com.calclab.hablar.roster.client;

import static com.calclab.hablar.roster.client.HablarRoster.i18n;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.changegroups.ManageGroupsPresenter;
import com.calclab.hablar.roster.client.changegroups.ManageGroupsWidget;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;

public class HablarManageGroups {
    private static final String ACTION_ID_ADD_TO_GROUP = "HablarRoster-manageGroupsAction";

    public static void install(final RosterPage roster, final Hablar hablar) {
	final ManageGroupsPresenter movePage = new ManageGroupsPresenter(hablar.getEventBus(), new ManageGroupsWidget());
	hablar.addPage(movePage, OverlayContainer.ROL);

	final String title = i18n().changeContactGroups();
	final SimpleAction<RosterItemPresenter> addToGroup = new SimpleAction<RosterItemPresenter>(title,
		ACTION_ID_ADD_TO_GROUP, HablarIcons.get(IconType.roster)) {
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
	roster.getItemMenu().addAction(addToGroup);
    }

}
