package com.calclab.hablar.icons.def.client;

import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class DefaultIconsEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	DefaultIconsBundle bundle = GWT.create(DefaultIconsBundle.class);
	Icons.register(HablarIcons.ADD, bundle.buddyAddIcon());
	Icons.register(HablarIcons.BUDDY, bundle.buddyIcon());
	// Icons.register(HablarIcons.BUDDY_DND, bundle.buddyIconDnd());
	// Icons.register(HablarIcons.BUDDY_OFF, bundle.buddyIconOff());
	// Icons.register(HablarIcons.BUDDY_ON, bundle.buddyIconOn());
	// Icons.register(HablarIcons.BUDDY_WAIT, bundle.buddyIconWait());
	// Icons.register(HablarIcons.ADD_CHAT, bundle.chatAddIcon());
	// Icons.register(HablarIcons.CHAT, bundle.chatIcon());
	// Icons.register(HablarIcons.CLIPBOARD, bundle.clipboardIcon());
	// Icons.register(HablarIcons.CLOSE, bundle.closeIcon());
	// Icons.register(HablarIcons.GROUP_ADD, bundle.groupAddIcon());
	// Icons.register(HablarIcons.GROUP_CHAT, bundle.groupChatIcon());
	// Icons.register(HablarIcons.LOADING, bundle.loadingIcon());
	// Icons.register(HablarIcons.MENU, bundle.menuIcon());
	// Icons.register(HablarIcons.DEFAULT, bundle.missingIcon());
	// Icons.register(HablarIcons.OFF, bundle.offIcon());
	// Icons.register(HablarIcons.ON, bundle.onIcon());
	// Icons.register(HablarIcons.ROSTER, bundle.rosterIcon());
	// Icons.register(HablarIcons.SEARCH, bundle.searchIcon());

    }

}
