package com.calclab.hablar.openchat.client.ui;

import static com.calclab.hablar.openchat.client.HablarOpenChat.i18n;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.validators.TextValidator;
import com.calclab.hablar.core.client.validators.Validators;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class OpenChatPresenter extends PagePresenter<OpenChatDisplay> {
    private static int index = 0;
    public static final String TYPE = "OpenChat";
    private final XmppRoster roster;
    private final TextValidator nameValidator;

    public OpenChatPresenter(final XmppSession session, final XmppRoster roster, final ChatManager chatManager,
	    final HablarEventBus eventBus, final OpenChatDisplay display) {
	super(TYPE, "" + ++index, eventBus, display);
	this.roster = roster;

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
		    chatManager.open(userJid);
		}
		if (display.getAddToRoster().getValue()) {
		    addToRoster(userJid);
		}
		requestVisibility(Visibility.hidden);
	    }
	});

	nameValidator = new TextValidator(display.getNameKeys(), display.getJabberId(), display.getJabberIdError(),
		display.getAcceptState());
	nameValidator.add(Validators.notEmpty(i18n().jabberIdIsEmpty()));
	nameValidator.add(Validators.isValidJid(i18n().jabberIdNotValid()));
	nameValidator.add(Validators.notCurrentUser(session, i18n().currentUserJidNotAllowed()));
    }

    protected void addToRoster(final XmppURI userJid) {
	roster.requestAddItem(userJid, userJid.getNode());
    }

    @Override
    protected void onBeforeFocus() {
	display.getJabberId().setText("");
	nameValidator.validate();
    }

}
