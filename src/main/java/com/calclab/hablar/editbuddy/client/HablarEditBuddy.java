package com.calclab.hablar.editbuddy.client;

import java.util.List;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.editbuddy.client.ui.EditBuddyWidget;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;


/**
 * Adds the ability to edit a buddy in the roster
 */
public class HablarEditBuddy {

	private static Action<RosterItemPresenter> createEditBuddyAction(final EditBuddyPage editBuddy) {
		return new SimpleAction<RosterItemPresenter>(EditBuddyMessages.msg.changeNickName(), "EditBuddy-editAction") {
			@Override
			public void execute(final RosterItemPresenter target) {
				editBuddy.setItem(target.getItem());
				editBuddy.requestVisibility(Visibility.focused);
			}
		};
	}

	public HablarEditBuddy(final Hablar hablar, final XmppRoster roster) {

		final EditBuddyPage editBuddy = new EditBuddyPage(roster, hablar.getEventBus(), new EditBuddyWidget());
		hablar.addPage(editBuddy, OverlayContainer.ROL);
		final List<Page<?>> rosters = hablar.getPagesOfType(RosterPage.TYPE);
		for (final Page<?> page : rosters) {
			final RosterPage rosterPage = (RosterPage) page;
			rosterPage.getItemMenu().addAction(createEditBuddyAction(editBuddy));
		}
	}

}
