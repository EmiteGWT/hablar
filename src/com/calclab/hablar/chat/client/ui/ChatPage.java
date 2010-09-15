package com.calclab.hablar.chat.client.ui;

import java.util.Date;

import com.calclab.hablar.chat.client.ChatMessageProvider;
import com.calclab.hablar.core.client.page.Page;

public interface ChatPage extends Page<ChatDisplay>, ChatMessageProvider {
    void addMessage(ChatMessage message);

    void addMessage(ChatMessage message, Date stamp);
}
