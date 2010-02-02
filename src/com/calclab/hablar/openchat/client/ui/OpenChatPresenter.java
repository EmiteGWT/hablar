package com.calclab.hablar.openchat.client.ui;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.hablar.basic.client.Hablar;
import com.calclab.hablar.basic.client.ui.Display;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class OpenChatPresenter {

    private final OpenChatDisplay display;
    private final ChatManager manager;

    public OpenChatPresenter(final Hablar hablar, final OpenChatDisplay display) {
	this.display = display;

	manager = Suco.get(ChatManager.class);

	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		hablar.closeOverlay();
	    }
	});

	display.getOpen().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String text = display.getJabberId().getText().trim();
		if (text.length() > 0) {
		    manager.open(XmppURI.jid(text));
		}
		hablar.closeOverlay();
	    }
	});
    }

    public Display getDisplay() {
	return display;
    }

}
