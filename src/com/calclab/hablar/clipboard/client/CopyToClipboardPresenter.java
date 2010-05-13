package com.calclab.hablar.clipboard.client;

import java.util.ArrayList;

import com.calclab.hablar.chat.client.MessageDataProvider;
import com.calclab.hablar.chat.client.ui.ChatMessageDisplay;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class CopyToClipboardPresenter extends PagePresenter<CopyToClipboardDisplay> {

    public static final String TYPE = "CopyToClipboard";

    public CopyToClipboardPresenter(final HablarEventBus eventBus, final CopyToClipboardDisplay display) {
	super(TYPE, eventBus, display);

	display.setCopyButtonVisible(false);

	display.getCopyButton().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getCloseButton().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});
    }

    public void setChatMessagesProvider(final MessageDataProvider provider) {
	final ArrayList<ChatMessageDisplay> messages = provider.getMessages();
	final StringBuilder text = new StringBuilder();
	for (final ChatMessageDisplay message : messages) {
	    text.append(message.getAuthor());
	    text.append(message.getBody());
	    text.append("\n");
	}
	display.getContentField().setText(text.toString());
    }

}
