package com.calclab.hablar.signals.client.unattended;

import com.google.gwt.event.shared.EventHandler;

public interface UnattendedMessageHandler extends EventHandler {

    void handleUnattendedMessage(UnattendedPagesChangedEvent event);

}
