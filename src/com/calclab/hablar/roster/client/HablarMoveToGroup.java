package com.calclab.hablar.roster.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.ui.groups.RosterItemPresenter;
import com.calclab.hablar.roster.client.ui.move.MoveToGroupPresenter;
import com.calclab.hablar.roster.client.ui.move.MoveToGroupWidget;

public class HablarMoveToGroup {
    private static final String ACTION_ID_ADD_TO_GROUP = "HablarRoster-addToGroupAction";

    public static void install(final RosterPage roster, final Hablar hablar) {
	final MoveToGroupPresenter movePage = new MoveToGroupPresenter(hablar.getEventBus(), new MoveToGroupWidget());
	hablar.addPage(movePage, OverlayContainer.ROL);
	// FIXME: i18n
	final String title = "Add to a group";
	final SimpleAction<RosterItemPresenter> addToGroup = new SimpleAction<RosterItemPresenter>(title,
		ACTION_ID_ADD_TO_GROUP, HablarIcons.get(IconType.buddy)) {
	    @Override
	    public void execute(final RosterItemPresenter target) {
		movePage.setItem(target.getItem());
		movePage.requestVisibility(Visibility.focused);
	    }

	    @Override
	    public boolean isApplicable(final RosterItemPresenter target) {
		return target.getGroupName().equals("");
	    }
	};
	roster.getItemMenu().addAction(addToGroup);
    }

}
