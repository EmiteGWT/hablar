package com.calclab.hablar.groupchat.client;

import com.calclab.hablar.chat.client.ui.ChatPage;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarGroupChat implements EntryPoint {
    private static final String ACTION_ID = "hablarGroupChat-convertToGroup";
    private static final String ACTION_ID_OPEN_GROUP = "hablarGroupChat-openGroupChat";

    public static SimpleAction<ChatPage> createConvertToGroupChatAction() {
	return new SimpleAction<ChatPage>("Convert to group", ACTION_ID, HablarIcons.get(IconType.buddyWait)) {
	    @Override
	    public void execute(final ChatPage target) {
		GWT.log("GROUP CHAT ACTION");
	    }
	};
    }

    protected static Action<RosterGroupPresenter> createOpenGroupChatAction() {
	return new SimpleAction<RosterGroupPresenter>("Open a group chat", ACTION_ID_OPEN_GROUP) {
	    @Override
	    public void execute(final RosterGroupPresenter target) {
	    }
	};
    }

    public static void install(final Hablar hablar) {
	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final Page<?> page = event.getPage();
		final ChatPage chat = ChatPage.asChat(page);
		if (chat != null) {
		    chat.addAction(createConvertToGroupChatAction());
		}
		final RosterPage roster = RosterPresenter.asRoster(page);
		if (roster != null) {
		    roster.getGroupMenu().addAction(createOpenGroupChatAction());
		}
	    }
	}, true);
    }

    @Override
    public void onModuleLoad() {
    }

}
