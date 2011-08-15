package com.calclab.hablar.openchat.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.icons.client.IconsBundle;
import com.calclab.hablar.openchat.client.ui.OpenChatPresenter;
import com.calclab.hablar.openchat.client.ui.OpenChatWidget;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;

/**
 * Adds the ability to open a chat with any jabber id.<br/>
 * This module adds:<br/>
 * 1. A button in the roster<br/>
 * 2. A overlay panel to write the jabber id
 * 
 */
public class HablarOpenChat {

	protected static final String ACTION_ID = "HablarOpenChat-openAction";

	private final Hablar hablar;
	private final XmppSession session;
	private final XmppRoster roster;
	private final ChatManager chatManager;

	public HablarOpenChat(final Hablar hablar, final XmppSession session, final XmppRoster roster, final ChatManager chatManager) {
		this.hablar = hablar;
		this.session = session;
		this.roster = roster;
		this.chatManager = chatManager;
		install();
	}

	private void install() {
		final OpenChatPresenter openChat = new OpenChatPresenter(session, roster, chatManager, hablar.getEventBus(), new OpenChatWidget());
		hablar.addPage(openChat, OverlayContainer.ROL);

		hablar.addPageAddedHandler(new PageAddedHandler() {
			@Override
			public void onPageAdded(final PageAddedEvent event) {
				final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
				if (rosterPage != null) {
					rosterPage.addAction(newOpenChatAction(openChat));
				}
			}
		}, true);
	}

	private SimpleAction<RosterPage> newOpenChatAction(final OpenChatPresenter openChat) {
		final String name = OpenChatMessages.msg.openNewChat();
		final SimpleAction<RosterPage> newOpenChatAction = new SimpleAction<RosterPage>(name, ACTION_ID, IconsBundle.bundle.chatAddIcon()) {
			@Override
			public void execute(final RosterPage page) {
				openChat.requestVisibility(Visibility.focused);
			}
		};
		return newOpenChatAction;
	}

}
