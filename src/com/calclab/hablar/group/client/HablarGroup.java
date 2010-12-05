package com.calclab.hablar.group.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.group.client.manage.CreateGroupPresenter;
import com.calclab.hablar.group.client.manage.ManageGroupPresenter;
import com.calclab.hablar.group.client.manage.ManageGroupWidget;
import com.calclab.hablar.group.client.manage.ModifyGroupPresenter;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * This object installs HablarGroup module inside hablar
 */
public class HablarGroup implements EntryPoint {
    private static final String ACTION_ID_MODIFY_GROUP = "HablarGroup-modifyGroup";
    protected static final String ACTION_ID_CREATE_GROUP = "HablarGroup-createGroup";
    private static GroupMessages groupMessages;

    public static GroupMessages i18n() {
	return groupMessages;
    }

    private final Hablar hablar;
    private final XmppSession session;
    private final XmppRoster roster;

    public HablarGroup(final Hablar hablar) {
	this.hablar = hablar;
	this.session = Suco.get(XmppSession.class);
	this.roster = Suco.get(XmppRoster.class);
	install();
    }

    private void install() {
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
	final String icon = Icons.ADD_GROUP;
	final SimpleAction<RosterPage> createGroupAction = new SimpleAction<RosterPage>(i18n().createGroupAction(),
		ACTION_ID_CREATE_GROUP, icon) {
	    @Override
	    public void execute(final RosterPage page) {
		createGroupPresenter.requestVisibility(Visibility.focused);
	    }
	};
	return createGroupAction;
    }

    private CreateGroupPresenter newCreateGroupPresenter() {
	return new CreateGroupPresenter(session, roster, hablar.getEventBus(), new ManageGroupWidget(), groupMessages
		.createNewGroup());
    }

    /**
     * Creates an action to modify the group
     * 
     * @param modifyGroupPresenter
     *            the presenter used to modify the group
     * @return the action
     */
    private SimpleAction<RosterGroupPresenter> newModifyGroupAction(final ModifyGroupPresenter modifyGroupPresenter) {
	final SimpleAction<RosterGroupPresenter> modifyGroupAction = new SimpleAction<RosterGroupPresenter>(i18n()
		.modifyGroupAction(), ACTION_ID_MODIFY_GROUP) {

	    @Override
	    public void execute(final RosterGroupPresenter target) {
		modifyGroupPresenter.setOldGroupName(target.getGroupName());
		modifyGroupPresenter.requestVisibility(Visibility.focused);
	    }
	};
	return modifyGroupAction;
    }

    private ModifyGroupPresenter newModifyGroupPresenter() {
	return new ModifyGroupPresenter(hablar.getEventBus(), new ManageGroupWidget(), groupMessages.modifyGroup());
    }

    @Override
    public void onModuleLoad() {
	final GroupMessages messages = (GroupMessages) GWT.create(GroupMessages.class);
	groupMessages = messages;
	ManageGroupWidget.setMessages(messages);
	ManageGroupPresenter.setMessages(messages);
    }
}
