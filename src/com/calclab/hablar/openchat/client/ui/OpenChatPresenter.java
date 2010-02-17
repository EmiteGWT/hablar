package com.calclab.hablar.openchat.client.ui;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;

public class OpenChatPresenter extends PagePresenter<OpenChatDisplay> {
    private static int index = 0;
    public static final String TYPE = "OpenChat";
    private final ChatManager manager;
    private final Roster roster;

    public OpenChatPresenter(final HablarEventBus eventBus, final OpenChatDisplay display) {
	super(TYPE, "" + ++index, eventBus, display);

	manager = Suco.get(ChatManager.class);
	roster = Suco.get(Roster.class);

	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getOpen().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final String text = display.getJabberId().getText().trim();
		final XmppURI userJid = XmppURI.jid(text);
		if (text.length() > 0) {
		    manager.open(userJid);
		}
		if (display.getAddToRoster().getValue()) {
		    addToRoster(userJid);
		}
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getNameKeys().addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(final KeyPressEvent event) {
		DeferredCommand.addCommand(new Command() {
		    @Override
		    public void execute() {
			final String value = display.getJabberId().getText().trim();
			display.setAcceptEnabled(value.length() > 0);
		    }
		});
	    }
	});
    }

    protected void addToRoster(final XmppURI userJid) {
	roster.requestAddItem(userJid, userJid.getNode());
    }

    @Override
    protected void onBeforeFocus() {
	display.setAcceptEnabled(false);
    }

}
