package com.calclab.hablar.chat.client;

import java.util.ArrayList;

import com.calclab.hablar.chat.client.ui.ChatMessage;

/**
 * Interface for classes which store messages. This is used to provide data to
 * the copy to clipboard functionality (or some other in the future)
 */
public interface ChatMessageProvider {
    ArrayList<ChatMessage> getMessages();
}
