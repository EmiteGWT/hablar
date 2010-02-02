package com.calclab.hablar.basic.client.ui.page.events;

import com.google.gwt.event.shared.EventHandler;

public interface UserMessageHandler extends EventHandler {

    void onUserMessage(UserMessageEvent event);

}
