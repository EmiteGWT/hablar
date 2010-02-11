package com.calclab.hablar.openchat.client;

import java.util.List;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.openchat.client.ui.OpenChatPage;
import com.calclab.hablar.openchat.client.ui.OpenChatWidget;
import com.calclab.hablar.roster.client.RosterPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Adds the ability to open a chat with any jabber id.<br/>
 * This module adds:<br/>
 * 1. A button in the roster<br/>
 * 2. A overlay panel to write the jabber id
 * 
 */
public class HablarOpenChat implements EntryPoint {

    public static void install(final Hablar hablar) {
	final OpenChatPage openChat = new OpenChatPage(hablar.getEventBus(), new OpenChatWidget());
	hablar.addPage(openChat, OverlayContainer.ROL);

	String iconStyle = HablarIcons.get(IconType.chatAdd);
	List<Page<?>> rosters = hablar.getPagesOfType(RosterPage.TYPE);
	for (Page<?> roster : rosters) {
	    ((RosterPage) roster).addAction(iconStyle, "HablarOpenChat-openAction", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    openChat.requestVisibility(Visibility.focused);
		}
	    });
	}
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    @Override
    public void onModuleLoad() {
    }

}
