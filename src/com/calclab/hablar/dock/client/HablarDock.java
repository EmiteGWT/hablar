package com.calclab.hablar.dock.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.Hablar.Chain;
import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.container.main.MainContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;

public class HablarDock {

    public static enum Position {
	left, right
    }

    public HablarDock(final Hablar hablar, final DockConfig config) {
	final HablarEventBus eventBus = hablar.getEventBus();
	final PagesContainer main = hablar.getContainer(MainContainer.ROL);
	final VariableDockContainer container = new VariableDockContainer(eventBus, config, main, hablar.getDisplay());
	hablar.addContainer(container, Chain.before);
    }

}
