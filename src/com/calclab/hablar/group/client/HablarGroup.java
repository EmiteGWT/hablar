package com.calclab.hablar.group.client;

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
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarGroup implements EntryPoint {
    private static final String ACTION_ID_MODIFY_GROUP = "HablarGroup-modifyGroup";
    protected static final String ACTION_ID_CREATE_GROUP = "HablarGroup-createGroup";
    private static GroupMessages groupMessages;

    public static GroupMessages i18n() {
	return groupMessages;
    }

    public static void install(final Hablar hablar) {
	final CreateGroupPresenter createGrouppresenter = new CreateGroupPresenter(hablar.getEventBus(),
		new ManageGroupWidget(), i18n().createNewGroup());
	final ModifyGroupPresenter modifyGroupPresenter = new ModifyGroupPresenter(hablar.getEventBus(),
		new ManageGroupWidget(), i18n().modifyGroup());
	hablar.addPage(createGrouppresenter, OverlayContainer.ROL);
	hablar.addPage(modifyGroupPresenter, OverlayContainer.ROL);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {

		    final String icon = Icons.ADD_GROUP;
		    rosterPage.addAction(new SimpleAction<RosterPage>(i18n().createGroupAction(),
			    ACTION_ID_CREATE_GROUP, icon) {
			@Override
			public void execute(final RosterPage page) {
			    createGrouppresenter.requestVisibility(Visibility.focused);
			}
		    });
		    rosterPage.getGroupMenu().addAction(
			    new SimpleAction<RosterGroupPresenter>(i18n().modifyGroupAction(), ACTION_ID_MODIFY_GROUP) {

				@Override
				public void execute(final RosterGroupPresenter target) {
				    modifyGroupPresenter.setOldGroupName(target.getGroupName());
				    modifyGroupPresenter.requestVisibility(Visibility.focused);
				}
			    });
		}
	    }
	}, true);
    }

    @Override
    public void onModuleLoad() {
	final GroupMessages messages = (GroupMessages) GWT.create(GroupMessages.class);
	groupMessages = messages;
	ManageGroupWidget.setMessages(messages);
	ManageGroupPresenter.setMessages(messages);
    }
}
