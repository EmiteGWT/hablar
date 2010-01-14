package com.calclab.hablar.client.chat;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.hablar.client.ui.page.Page;

public interface ChatView extends Page {

    enum MessageType {
	incoming, sent
    }

    void clearAndFocus();

    void setHeaderTitle(String name);

    void setPresence(Show show);

    void showMessage(String name, String body, MessageType type);

}
