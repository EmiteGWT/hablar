package com.calclab.hablar.chat.client.ui;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.hablar.core.client.ui.menu.Action;

public interface PairChatPage extends ChatPage {

    void addAction(Action<PairChatPage> action);

    Chat getChat();

    String getChatName();

    void setPresence(boolean available, Show show);

}
