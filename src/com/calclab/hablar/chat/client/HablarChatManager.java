package com.calclab.hablar.chat.client;

import java.util.HashMap;
import java.util.Set;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.chat.client.ui.PairChatPage;
import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.chat.client.ui.ChatWidget;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class HablarChatManager {

    public static interface ChatPageFactory {

	ChatDisplay create(boolean sendButtonVisible);
    }
    private final HashMap<XmppURI, PairChatPage> chatPages;

    private final Roster roster;
    private final ChatPageFactory factory;
    private final boolean sendButtonVisible;

    private final Hablar hablar;

    public HablarChatManager(final Hablar hablar, final ChatConfig config) {
	this(hablar, config, new ChatPageFactory() {
	    @Override
	    public ChatDisplay create(final boolean sendButtonVisible) {
		return new ChatWidget(sendButtonVisible);
	    }
	});
    }

    public HablarChatManager(final Hablar hablarPresenter, final ChatConfig config, final ChatPageFactory factory) {
	hablar = hablarPresenter;
	this.factory = factory;
	chatPages = new HashMap<XmppURI, PairChatPage>();

	Suco.get(Session.class); // To fix recursion.
	roster = Suco.get(Roster.class);
	final ChatManager chatManager = Suco.get(ChatManager.class);

	if (config.openChat != null) {
	    chatManager.open(config.openChat);
	}

	chatManager.onChatCreated(new Listener<Chat>() {
	    @Override
	    public void onEvent(final Chat chat) {
		createChat(chat, Visibility.notFocused);
	    }
	});

	chatManager.onChatOpened(new Listener<Chat>() {
	    @Override
	    public void onEvent(final Chat chat) {
		final PairChatPage page = chatPages.get(chat.getURI());
		assert page != null;
		page.requestVisibility(Visibility.focused);
	    }
	});

	chatManager.onChatClosed(new Listener<Chat>() {
	    @Override
	    public void onEvent(final Chat chat) {
		final PairChatPage page = chatPages.get(chat.getURI());
		page.requestVisibility(Visibility.hidden);
	    }
	});

	roster.onItemChanged(new Listener<RosterItem>() {
	    public void onEvent(final RosterItem item) {
		final XmppURI jid = item.getJID();
		final Set<XmppURI> chats = chatPages.keySet();
		for (final XmppURI chatURI : chats) {
		    if (chatURI.equalsNoResource(jid)) {
			final PairChatPage page = chatPages.get(chatURI);
			page.setPresence(item.isAvailable(), item.getShow());
		    }
		}
	    }
	});

	sendButtonVisible = config.sendButtonVisible;

    }

    private void createChat(final Chat chat, final Visibility visibility) {
	final ChatDisplay display = factory.create(sendButtonVisible);
	final PairChatPage presenter = new PairChatPresenter(hablar.getEventBus(), chat, display);
	chatPages.put(chat.getURI(), presenter);
	hablar.addPage(presenter);

	final RosterItem item = roster.getItemByJID(chat.getURI().getJID());
	final Show show = item != null ? item.getShow() : Show.unknown;
	final boolean available = item != null ? item.isAvailable() : false;
	presenter.setPresence(available, show);
    }

}
