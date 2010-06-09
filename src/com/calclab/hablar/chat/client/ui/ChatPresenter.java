package com.calclab.hablar.chat.client.ui;

import java.util.ArrayList;
import java.util.Date;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.validators.IsEmpty;
import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Shared code for different chat presenters (currently PairChatPresenter and
 * RoomPresenter)
 * 
 */
public abstract class ChatPresenter extends PagePresenter<ChatDisplay> implements ChatPage {
    private final ArrayList<ChatMessage> messages;

    public ChatPresenter(final String pageType, final String id, final HablarEventBus eventBus,
	    final ChatDisplay display) {
	super(pageType, id, eventBus, display);
	messages = new ArrayList<ChatMessage>();
    }

    @Override
    public void addMessage(final ChatMessage message) {
	final Date today = new Date();
	message.metadata = DateTimeFormat.getShortTimeFormat().format(today);
	messages.add(message);
	display.addMessage(message);
    }

    @Override
    public ArrayList<ChatMessage> getMessages() {
	return messages;
    }

    protected void sendMessage(final Chat chat, final ChatDisplay display) {
	final String text = display.getBody().getText().trim();
	if (IsEmpty.not(text)) {
	    addMessage(new ChatMessage(null, "me", text, ChatMessage.MessageType.sent));
	    chat.send(new Message(text));
	    display.clearAndFocus();
	}
    }

    protected void setState(final State state) {
	final boolean visible = state == State.ready;
	display.setControlsVisible(visible);
	display.setStatusVisible(visible);
    }

}
