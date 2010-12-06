package com.calclab.hablar.roster.client;

import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.session.SessionStates;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionHandler;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.im.client.roster.SubscriptionHandler.Behaviour;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.roster.client.page.RosterWidget;
import com.calclab.suco.client.Suco;

public class HablarRoster {

    private static RosterMessages rosterMessages;

    public static RosterMessages i18n() {
	return rosterMessages;
    }

    public static void setMessages(final RosterMessages messages) {
	rosterMessages = messages;
    }

    private final RosterPage rosterPage;

    // FIXME: move to gin
    @SuppressWarnings("deprecation")
    public HablarRoster(final Hablar hablar, final RosterConfig rosterConfig) {
	final XmppSession session = Suco.get(XmppSession.class);
	final XmppRoster roster = Suco.get(XmppRoster.class);
	final SubscriptionHandler subscriptionHandler = Suco.get(SubscriptionHandler.class);
	final ChatManager chatManager = Suco.get(ChatManager.class);

	subscriptionHandler.setBehaviour(Behaviour.acceptAll);

	if ((rosterConfig.rosterItemClickAction == null) && rosterConfig.oneClickChat) {
	    rosterConfig.rosterItemClickAction = new SimpleAction<RosterItem>(i18n().clickToChatWith(),
		    "rosterItemClickAction") {
		@Override
		public void execute(final RosterItem item) {
		    chatManager.open(item.getJID());
		}
	    };
	}

	rosterPage = new RosterPresenter(session, roster, chatManager, hablar.getEventBus(), new RosterWidget(),
		rosterConfig);
	rosterPage.setVisibility(Visibility.notFocused);
	hablar.addPage(rosterPage);

	session.addSessionStateChangedHandler(true, new StateChangedHandler() {
	    @Override
	    public void onStateChanged(final StateChangedEvent event) {
		if (SessionStates.ready.equals(event.getState())) {
		    rosterPage.requestVisibility(Visibility.focused);
		}
	    }
	});
	rosterPage.addHighPriorityActions();
    }

    public void addLowPriorityActions() {
	rosterPage.addLowPriorityActions();
    }

    public RosterPage getRosterPage() {
	return rosterPage;
    }
}
