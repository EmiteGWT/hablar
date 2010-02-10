package com.calclab.hablar.dock.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.Hablar.Chain;
import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.container.main.MainContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.google.gwt.core.client.EntryPoint;

public class HablarDock implements EntryPoint {

    public static enum Position {
	left, right
    }

    public static void install(Hablar hablar, DockConfig config) {
	HablarEventBus eventBus = hablar.getEventBus();
	PagesContainer main = hablar.getContainer(MainContainer.ROL);
	DockContainer container = new DockContainer(eventBus, config, new DockLayout(main.getWidget(), hablar
		.getDisplay()));
	hablar.addContainer(container, Chain.before);
    }

    public static void install(HablarWidget widget, DockConfig config) {
	install(widget.getHablar(), config);
    }

    @Override
    public void onModuleLoad() {

    }

}
