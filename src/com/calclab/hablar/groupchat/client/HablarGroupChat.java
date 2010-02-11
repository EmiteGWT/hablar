package com.calclab.hablar.groupchat.client;

import com.calclab.hablar.chat.client.ui.ChatPage;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.google.gwt.core.client.EntryPoint;

public class HablarGroupChat implements EntryPoint {

    public static void install(Hablar hablar) {
	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(PageAddedEvent event) {
		if (ChatPage.TYPE.equals(event.getPage().getType())) {
		    // add a button to convert a chat to a group chat in the
		    // chat
		    // panel
		    ChatPage chat = (ChatPage) event.getPage();
		    chat.addAction();
		}
	    }
	}, true);
    }

    @Override
    public void onModuleLoad() {
    }

}
