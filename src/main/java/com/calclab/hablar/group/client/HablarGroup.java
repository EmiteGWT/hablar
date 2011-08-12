package com.calclab.hablar.group.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.group.client.manage.CreateGroupPresenter;
import com.calclab.hablar.group.client.manage.ManageGroupWidget;
import com.calclab.hablar.group.client.manage.ModifyGroupPresenter;
import com.calclab.hablar.icons.client.IconsBundle;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.google.inject.Inject;

/**
 * This object installs HablarGroup module inside hablar
 */
public class HablarGroup {
	private static final String ACTION_ID_MODIFY_GROUP = "HablarGroup-modifyGroup";
	protected static final String ACTION_ID_CREATE_GROUP = "HablarGroup-createGroup";

	private final Hablar hablar;
	private final XmppSession session;
	private final XmppRoster roster;

	@Inject
	public HablarGroup(final Hablar hablar, final XmppSession session, final XmppRoster roster) {
		this.hablar = hablar;
		this.session = session;
		this.roster = roster;

		final CreateGroupPresenter createGrouppresenter = newCreateGroupPresenter();
		final ModifyGroupPresenter modifyGroupPresenter = newModifyGroupPresenter();
		hablar.addPage(createGrouppresenter, OverlayContainer.ROL);
		hablar.addPage(modifyGroupPresenter, OverlayContainer.ROL);

		hablar.addPageAddedHandler(new PageAddedHandler() {
			@Override
			public void onPageAdded(final PageAddedEvent event) {
				final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
				if (rosterPage != null) {
					rosterPage.addAction(newCreateGroupAction(createGrouppresenter));
					rosterPage.getGroupMenu().addAction(newModifyGroupAction(modifyGroupPresenter));
				}
			}
		}, true);
	}

	/**
	 * Creates an action to create a new group
	 * 
	 * @param createGroupPresenter
	 *            the group presenter used
	 * @return the action
	 */
	private SimpleAction<RosterPage> newCreateGroupAction(final CreateGroupPresenter createGroupPresenter) {
		final SimpleAction<RosterPage> createGroupAction = new SimpleAction<RosterPage>(GroupMessages.msg.createGroupAction(), ACTION_ID_CREATE_GROUP, IconsBundle.bundle.groupAddIcon()) {
			@Override
			public void execute(final RosterPage page) {
				createGroupPresenter.requestVisibility(Visibility.focused);
			}
		};
		return createGroupAction;
	}

	private CreateGroupPresenter newCreateGroupPresenter() {
		return new CreateGroupPresenter(session, roster, hablar.getEventBus(), new ManageGroupWidget(), GroupMessages.msg.createNewGroup());
	}

	/**
	 * Creates an action to modify the group
	 * 
	 * @param modifyGroupPresenter
	 *            the presenter used to modify the group
	 * @return the action
	 */
	private SimpleAction<RosterGroupPresenter> newModifyGroupAction(final ModifyGroupPresenter modifyGroupPresenter) {
		final SimpleAction<RosterGroupPresenter> modifyGroupAction = new SimpleAction<RosterGroupPresenter>(GroupMessages.msg.modifyGroupAction(),
				ACTION_ID_MODIFY_GROUP) {
			@Override
			public void execute(final RosterGroupPresenter target) {
				modifyGroupPresenter.setOldGroupName(target.getGroupName());
				modifyGroupPresenter.requestVisibility(Visibility.focused);
			}
		};
		return modifyGroupAction;
	}

	private ModifyGroupPresenter newModifyGroupPresenter() {
		return new ModifyGroupPresenter(roster, hablar.getEventBus(), new ManageGroupWidget(), GroupMessages.msg.modifyGroup());
	}

}
