package com.calclab.hablar.chat.client;

import java.util.HashMap;
import java.util.Map.Entry;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.chat.events.ChatChangedEvent;
import com.calclab.emite.im.client.chat.events.ChatChangedHandler;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.chat.client.ui.ChatWidget;
import com.calclab.hablar.chat.client.ui.PairChatPage;
import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class HablarChatManager {

	/**
	 * Factory to create a chat display
	 */
	public static interface ChatPageFactory {
		/**
		 * Create a ChatDisplay instance.
		 * 
		 * @param sendButtonVisible whether the "Send" button should be visible.
		 * @return the ChatDisplay instance.
		 */
		ChatDisplay create(boolean sendButtonVisible);
	}
	
	public static interface PairChatPresenterFactory {
		/**
		 * Create a ChatPresenter instance.
		 * 
		 * @param sendButtonVisible whether the "Send" button should be visible.
		 * @return the ChatDisplay instance.
		 */
		PairChatPresenter create(HablarEventBus eventBus, Chat chat, ChatDisplay display);
	}

	private final HashMap<Chat, PairChatPage> chatPages;

	private final Roster roster;
	private final ChatPageFactory chatPageFactory;
	private final PairChatPresenterFactory chatPresenterFactory;
	private final boolean sendButtonVisible;

	private final Hablar hablar;

	public HablarChatManager(final Hablar hablar, final ChatConfig config) {
		this(hablar, config, new ChatPageFactory() {
			@Override
			public ChatDisplay create(final boolean sendButtonVisible) {
				return new ChatWidget(sendButtonVisible);
			}
		},
		new PairChatPresenterFactory() {
			
			@Override
			public PairChatPresenter create(HablarEventBus eventBus, Chat chat,
					ChatDisplay display) {
				return new PairChatPresenter(eventBus, chat, display);
			}
		}
		);
	}

	public HablarChatManager(final Hablar hablarPresenter,
			final ChatConfig config, final ChatPageFactory chatPageFactory, final PairChatPresenterFactory chatPresenterFactory) {
		hablar = hablarPresenter;
		this.chatPageFactory = chatPageFactory;
		this.chatPresenterFactory = chatPresenterFactory;
		chatPages = new HashMap<Chat, PairChatPage>();

		roster = Suco.get(Roster.class);
		final ChatManager chatManager = Suco.get(ChatManager.class);

		if (config.openChat != null) {
			chatManager.open(config.openChat);
		}
		
		chatManager.addChatChangedHandler(new ChatChangedHandler() {
			
			@Override
			public void onChatChanged(ChatChangedEvent event) {
				if(event.isCreated()) {
					createChat(event.getChat(), Visibility.notFocused);
				}
				
				if(event.isOpened()) {
					final PairChatPage page = chatPages.get(event.getChat());
					if(page != null) {
						page.requestVisibility(Visibility.focused);
					} else {
						createChat(event.getChat(), Visibility.notFocused);
					}
				}
				
				if(event.isClosed()) {
					final PairChatPage page = chatPages.get(event.getChat());
					page.requestVisibility(Visibility.hidden);
				}
			}
		});
		
		roster.onItemChanged(new Listener<RosterItem>() {
			@Override
			public void onEvent(final RosterItem item) {
				final XmppURI jid = item.getJID();
				for (final Entry<Chat, PairChatPage> entry : chatPages.entrySet()) {
					if (entry.getKey().getURI().equalsNoResource(jid)) {
						entry.getValue().setPresence(item.isAvailable(), item.getShow());
					}
				}
			}
		});

		sendButtonVisible = config.sendButtonVisible;

	}

	private void createChat(final Chat chat, final Visibility visibility) {
		final ChatDisplay display = chatPageFactory.create(sendButtonVisible);
		final PairChatPresenter presenter = chatPresenterFactory.create(hablar.getEventBus(), chat, display) ;
		chatPages.put(chat, presenter);
		hablar.addPage(presenter);

		final RosterItem item = roster.getItemByJID(chat.getURI().getJID());
		final Show show = item != null ? item.getShow() : Show.unknown;
		final boolean available = item != null ? item.isAvailable() : false;
		presenter.setPresence(available, show);
	}

}
