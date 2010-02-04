package com.calclab.hablar.openchat.client;

import java.util.List;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.pages.OverlayContainer;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.openchat.client.ui.OpenChatPresenter;
import com.calclab.hablar.openchat.client.ui.OpenChatWidget;
import com.calclab.hablar.roster.client.RosterPresenter;
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
	final OpenChatPresenter openChat = new OpenChatPresenter(hablar.getEventBus(), new OpenChatWidget());
	hablar.addPage(openChat, OverlayContainer.TYPE);

	String iconStyle = HablarIcons.get(IconType.chatAdd);
	List<PagePresenter<?>> rosters = hablar.getPagePresentersOfType(RosterPresenter.TYPE);
	for (Page<?> roster : rosters) {
	    ((RosterPresenter) roster).addAction(iconStyle, "HablarOpenChat-openAction", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    openChat.requestOpen();
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
