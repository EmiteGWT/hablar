package com.calclab.hablar.icons.ie6gif.client;

import com.calclab.hablar.core.client.ui.icon.Icons;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class IE6GifIcons implements EntryPoint {
    public static void load() {
	GWT.log("ICONS: DefaultIcons");
	final IE6GifIconsBundle bundle = GWT.create(IE6GifIconsBundle.class);
	Icons.register(Icons.BUDDY_ADD, bundle.buddyAddIcon());
	Icons.register(Icons.BUDDY, bundle.buddyIcon());
	Icons.register(Icons.BUDDY_DND, bundle.buddyIconDnd());
	Icons.register(Icons.BUDDY_OFF, bundle.buddyIconOff());
	Icons.register(Icons.BUDDY_ON, bundle.buddyIconOn());
	Icons.register(Icons.BUDDY_WAIT, bundle.buddyIconWait());
	Icons.register(Icons.ADD_CHAT, bundle.chatAddIcon());
	Icons.register(Icons.CHAT, bundle.chatIcon());
	Icons.register(Icons.CLIPBOARD, bundle.clipboardIcon());
	Icons.register(Icons.CLOSE, bundle.closeIcon());
	Icons.register(Icons.CONSOLE, bundle.consoleIcon());
	Icons.register(Icons.ADD_GROUP, bundle.groupAddIcon());
	Icons.register(Icons.GROUP_CHAT, bundle.groupChatIcon());
	Icons.register(Icons.GROUP_CHAT_ADD, bundle.groupChatAddIcon());
	Icons.register(Icons.LOADING, bundle.loadingIcon());
	Icons.register(Icons.MENU, bundle.menuIcon());
	Icons.register(Icons.MISSING_ICON, bundle.missingIcon());
	Icons.register(Icons.NOT_CONNECTED, bundle.offIcon());
	Icons.register(Icons.CONNECTED, bundle.onIcon());
	Icons.register(Icons.ROSTER, bundle.rosterIcon());
	Icons.register(Icons.SEARCH, bundle.searchIcon());
    }

    @Override
    public void onModuleLoad() {
    }

}
