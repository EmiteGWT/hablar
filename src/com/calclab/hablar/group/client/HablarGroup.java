package com.calclab.hablar.group.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.group.client.manage.ManageGroupPresenter;
import com.calclab.hablar.group.client.manage.ManageGroupWidget;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarGroup implements EntryPoint {
    protected static final String ACTION_ID = "HablarGroup-createGroup";
    private static GroupMessages groupMessages;

    public static GroupMessages i18n() {
	return groupMessages;
    }

    public static void install(final Hablar hablar) {
	final ManageGroupPresenter presenter = new ManageGroupPresenter(hablar.getEventBus(), new ManageGroupWidget(),
		i18n().createNewGroup());
	hablar.addPage(presenter, OverlayContainer.ROL);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {

		    final String name = i18n().createGroupAction();
		    final String icon = HablarIcons.get(IconType.groupAdd);
		    rosterPage.addAction(new SimpleAction<RosterPage>(name, ACTION_ID, icon) {
			@Override
			public void execute(final RosterPage page) {
			    presenter.requestVisibility(Visibility.focused);
			}
		    });
		}
	    }
	}, true);
    }

    @Override
    public void onModuleLoad() {
	GroupMessages messages = (GroupMessages) GWT.create(GroupMessages.class);
	groupMessages = messages;
	ManageGroupWidget.setMessages(messages);
	ManageGroupPresenter.setMessages(messages);
    }
}
