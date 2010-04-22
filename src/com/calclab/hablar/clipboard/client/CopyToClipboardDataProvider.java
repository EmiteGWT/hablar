package com.calclab.hablar.clipboard.client;

import java.util.ArrayList;

import com.calclab.hablar.chat.client.ui.ChatMessageDisplay;

/**
 * Interface for classes which can provide data to the copy to clipboard functionality
 */
public interface CopyToClipboardDataProvider {
    ArrayList<ChatMessageDisplay> getMessages();
}
