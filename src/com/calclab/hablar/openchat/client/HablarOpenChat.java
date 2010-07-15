package com.calclab.hablar.openchat.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.OldHablarIcons;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.openchat.client.ui.OpenChatPresenter;
import com.calclab.hablar.openchat.client.ui.OpenChatWidget;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;

/**
 * Adds the ability to open a chat with any jabber id.<br/>
 * This module adds:<br/>
 * 1. A button in the roster<br/>
 * 2. A overlay panel to write the jabber id
 *
 */
public class HablarOpenChat implements EntryPoint {

    protected static final String ACTION_ID = "HablarOpenChat-openAction";
    private static OpenChatMessages openChatMessages;

    public static OpenChatMessages i18n() {
	return openChatMessages;
    }

    public static void install(final Hablar hablar) {
	final OpenChatPresenter openChat = new OpenChatPresenter(hablar.getEventBus(), new OpenChatWidget());
	hablar.addPage(openChat, OverlayContainer.ROL);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {

		    final String name = i18n().openNewChat();
		    final ImageResource icon = OldHablarIcons.getBundle().chatAddIcon();
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

    public static void setMessages(final OpenChatMessages messages) {
	openChatMessages = messages;
    }

    @Override
    public void onModuleLoad() {
	OpenChatMessages messages = (OpenChatMessages) GWT.create(OpenChatMessages.class);
	HablarOpenChat.setMessages(messages);
	OpenChatWidget.setMessages(messages);
    }

}
