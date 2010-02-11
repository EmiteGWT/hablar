package com.calclab.hablar.openchat.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.openchat.client.ui.OpenChatPage;
import com.calclab.hablar.openchat.client.ui.OpenChatWidget;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.google.gwt.core.client.EntryPoint;

/**
 * Adds the ability to open a chat with any jabber id.<br/>
 * This module adds:<br/>
 * 1. A button in the roster<br/>
 * 2. A overlay panel to write the jabber id
 * 
 */
public class HablarOpenChat implements EntryPoint {

    protected static final String ACTION_ID = "HablarOpenChat-openAction";

    public static void install(final Hablar hablar) {
	final OpenChatPage openChat = new OpenChatPage(hablar.getEventBus(), new OpenChatWidget());
	hablar.addPage(openChat, OverlayContainer.ROL);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {

		    final String name = "Open New Chat";
		    final String icon = HablarIcons.get(IconType.chatAdd);
		    rosterPage.addAction(new SimpleAction<RosterPage>(name, ACTION_ID, icon) {
			@Override
			public void execute(final RosterPage page) {
			    openChat.requestVisibility(Visibility.focused);
			}
		    });
		}
	    }
	}, true);
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    @Override
    public void onModuleLoad() {
    }

}
