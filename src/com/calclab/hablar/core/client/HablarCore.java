package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.ui.prompt.ConfirmPage;
import com.calclab.hablar.core.client.ui.prompt.ConfirmWidget;
import com.google.inject.Inject;

public class HablarCore {

    @Inject
    public HablarCore(final Hablar hablar) {
	final ConfirmPage confirmPage = new ConfirmPage(hablar.getEventBus(), new ConfirmWidget());
	hablar.addPage(confirmPage, OverlayContainer.ROL);
    }
}
