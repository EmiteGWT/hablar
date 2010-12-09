package com.calclab.hablar.chat.client.ui;

import java.util.ArrayList;

import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatStates;
import com.calclab.hablar.core.client.browser.BrowserFocusHandler;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.validators.Empty;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;

/**
 * Shared code for different chat presenters (currently PairChatPresenter and
 * RoomPresenter). Not intended for instance directly
 * 
 */
public class ChatPresenter extends PagePresenter<ChatDisplay> implements ChatPage {
    private static final String ODD = "odd";
    private static final String EVEN = "even";
    private final ArrayList<ChatMessage> messages;
    private String currentClass;
    private String lastAuthor;

    public ChatPresenter(final String pageType, final String id, final HablarEventBus eventBus, final Chat chat,
	    final ChatDisplay display) {
	super(pageType, id, eventBus, display);
	messages = new ArrayList<ChatMessage>();
	this.currentClass = ODD;
	this.lastAuthor = null;

	display.getTextBoxFocus().addFocusHandler(new FocusHandler() {
	    @Override
	    public void onFocus(final FocusEvent event) {
		setVisibility(Visibility.focused);
	    }
	});

	chat.addChatStateChangedHandler(true, new StateChangedHandler() {
	    @Override
	    public void onStateChanged(final StateChangedEvent event) {
		setState(event.getState());
	    }
	});
    }

    /**
     * Add message to the chat display.
     */
    @Override
    public void addMessage(final ChatMessage message) {
	final boolean sameAuthor = (lastAuthor == null ? message.author == null : lastAuthor.equals(message.author));
	if (!sameAuthor) {
	    currentClass = currentClass == EVEN ? ODD : EVEN;
	    lastAuthor = message.author;
	}
	messages.add(message);
	display.addMessage(message, currentClass);
    }

    @Override
    public ArrayList<ChatMessage> getMessages() {
	return messages;
    }

    @Override
    public void setVisibility(final Visibility visibility) {
	super.setVisibility(visibility);
	if ((visibility == Visibility.focused) && BrowserFocusHandler.getInstance().hasFocus()) {
	    display.setTextBoxFocus(true);
	}
    }

    protected void sendMessage(final Chat chat, final ChatDisplay display) {
	final String text = display.getBody().getText().trim();
	if (Empty.not(text)) {
	    final ChatMessage message = new ChatMessage("me", text, ChatMessage.MessageType.sent);
	    message.color = ColorHelper.ME;
	    addMessage(message);
	    chat.send(new Message(text));
	    display.clearAndFocus();
	}
    }

    protected void setState(final String chatState) {
	final boolean visible = ChatStates.ready.equals(chatState);
	display.setControlsVisible(visible);
	display.setStatusVisible(visible);
    }

}
