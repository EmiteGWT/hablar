package com.calclab.hablar.chat.client.ui;

import java.util.ArrayList;
import java.util.Date;

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
import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Shared code for different chat presenters (currently PairChatPresenter and
 * RoomPresenter). Not intended for instance directly
 * 
 */
public class ChatPresenter extends PagePresenter<ChatDisplay> implements ChatPage {
    private final ArrayList<ChatMessage> messages;
    private boolean showDate;

    public ChatPresenter(final String pageType, final String id, final HablarEventBus eventBus, final Chat chat,
	    final ChatDisplay display) {
	super(pageType, id, eventBus, display);
	messages = new ArrayList<ChatMessage>();
	showDate = true;

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
	addMessage(message, null);
    }

    /**
     * Add message to the chat display.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void addMessage(final ChatMessage message, final Date stamp) {
	Date date = stamp;
	final Date now = new Date();
	DateTimeFormat format;
	if (date == null) {
	    date = now;
	    format = DateTimeFormat.getShortTimeFormat();
	} else if ((date.getYear() == now.getYear()) && (date.getMonth() == now.getMonth())
		&& (date.getDate() == now.getDate())) {
	    format = DateTimeFormat.getShortTimeFormat();
	} else {
	    format = DateTimeFormat.getShortDateTimeFormat();
	}

	if (showDate) {
	    message.metadata = format.format(date);
	}
	messages.add(message);
	display.addMessage(message);
    }

    @Override
    public ArrayList<ChatMessage> getMessages() {
	return messages;
    }

    protected void sendMessage(final Chat chat, final ChatDisplay display) {
	final String text = display.getBody().getText().trim();
	if (Empty.not(text)) {
	    addMessage(new ChatMessage(null, ColorHelper.ME, "me", text, ChatMessage.MessageType.sent));
	    chat.send(new Message(text));
	    display.clearAndFocus();
	}
    }

    public void setShowDate(final boolean showDate) {
	this.showDate = showDate;
    }

    protected void setState(final String chatState) {
	final boolean visible = ChatStates.ready.equals(chatState);
	display.setControlsVisible(visible);
	display.setStatusVisible(visible);
    }

    @Override
    public void setVisibility(final Visibility visibility) {
	super.setVisibility(visibility);
	if ((visibility == Visibility.focused) && BrowserFocusHandler.getInstance().hasFocus()) {
	    display.setTextBoxFocus(true);
	}
    }

}
