package com.calclab.hablar.signals.client;

import com.google.gwt.event.shared.EventHandler;

public interface UnattendedMessageHandler extends EventHandler {

    void handleUnattendedMessage(UnattendedChatsChangedEvent event);

}
