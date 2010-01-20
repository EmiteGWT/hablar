package com.calclab.hablar.signals.client;

import java.util.Set;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.chat.client.ChatPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.user.client.Window;

public class WindowTitleManager {
    public static String ellipsis(final String text, final int length) {
	// FIXME this must go in TextUtils
	return text.length() > length ? text.substring(0, length - 3) + "..." : text;
    }

    public WindowTitleManager(final ChatSignalsLogic chatSignals) {
	// This can go in another different module (and you can do another kind
	// of title modifications)
	final Msg i18n = Suco.get(Msg.class);
	chatSignals.addChatsUnattendedChanged(new Listener<Set<PageView>>() {
	    @Override
	    public void onEvent(final Set<PageView> set) {
		final int size = set.size();
		WindowTitleHelper.addPrefix(size == 0 ? "" : i18n.unreadChats(size));
	    }
	});
	chatSignals.addNewUnattendedChat(new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView chatPage) {
		final XmppURI fromURI = ((ChatPage) chatPage).getChat().getURI();
		// FIXME: userJid.getNode() can be null them
		// duplicate code form ChatLogic (this must go in
		// XmmpURI.shortName() or something similar
		// whe should revise anyother call to jid.getNode() alone to
		// find bugs
		final String name = fromURI.getNode() == null ? fromURI.getHost() : fromURI.getNode();
		final String msg = chatPage.getStatusMessage();
		Window.alert(i18n.newChatFrom(name, ellipsis(msg, 30)));
	    }
	});
    }
}
