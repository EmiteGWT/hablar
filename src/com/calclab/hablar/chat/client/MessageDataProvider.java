package com.calclab.hablar.chat.client;

import java.util.ArrayList;

import com.calclab.hablar.chat.client.ui.ChatMessageDisplay;

/**
 * Interface for classes which store messages. This is used to provide data to
 * the copy to clipboard functionality.
 */
public interface MessageDataProvider {
    ArrayList<ChatMessageDisplay> getMessages();
}
