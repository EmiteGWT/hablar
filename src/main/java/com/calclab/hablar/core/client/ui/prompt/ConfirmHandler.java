package com.calclab.hablar.core.client.ui.prompt;

import com.google.gwt.event.shared.EventHandler;

public interface ConfirmHandler extends EventHandler {
    void onConfirm(ConfirmEvent event);
}
