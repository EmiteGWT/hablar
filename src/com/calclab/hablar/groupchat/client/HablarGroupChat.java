package com.calclab.hablar.groupchat.client;

import com.calclab.hablar.chat.client.ui.ChatPage;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarGroupChat implements EntryPoint {
    private static final String ACTION_ID = "hablarGroupChat-convertToGroup";

    public static void install(Hablar hablar) {
	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(PageAddedEvent event) {
		ChatPage chat = ChatPage.asChat(event.getPage());
		if (chat != null) {
		    chat.addAction(createAction());
		}
	    }
	}, true);
    }

    @Override
    public void onModuleLoad() {
    }

    public static SimpleAction<ChatPage> createAction() {
	return new SimpleAction<ChatPage>("Convert to group", ACTION_ID, HablarIcons.get(IconType.buddyWait)) {
	    @Override
	    public void execute(ChatPage target) {
		GWT.log("GROUP CHAT ACTION");
	    }
	};
    }

}
