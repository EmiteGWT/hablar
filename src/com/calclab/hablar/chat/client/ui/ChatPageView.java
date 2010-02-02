package com.calclab.hablar.chat.client.ui;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.hablar.basic.client.ui.page.PageView;

public interface ChatPageView extends PageView {

    enum MessageType {
	incoming, sent
    }

    void clearAndFocus();

    void setControlsVisible(boolean visible);

    void setHeaderTitle(String name);

    void setPresence(boolean available, Show show);

    void showMessage(String name, String body, MessageType type);

}
