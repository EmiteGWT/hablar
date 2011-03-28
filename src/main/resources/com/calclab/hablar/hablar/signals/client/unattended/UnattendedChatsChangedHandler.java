package com.calclab.hablar.signals.client.unattended;

import com.google.gwt.event.shared.EventHandler;

public interface UnattendedChatsChangedHandler extends EventHandler {

    void handleUnattendedChatChange(UnattendedChatsChangedEvent event);

}
