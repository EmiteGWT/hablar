package com.calclab.hablar.dock.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.pages.MainContainer;
import com.calclab.hablar.core.client.pages.PagesContainer;
import com.google.gwt.core.client.EntryPoint;

public class HablarDock implements EntryPoint {

    public static enum Position {
	left, right
    }

    public static void install(Hablar hablar, DockConfig config) {
	PagesContainer main = hablar.getContainer(MainContainer.ROL);
	DockContainer dock = new DockContainer(config, main.getWidget(), hablar.getDisplay().getContainer());
	hablar.addContainer(dock);
    }

    public static void install(HablarWidget widget, DockConfig config) {
	install(widget.getHablar(), config);
    }

    @Override
    public void onModuleLoad() {

    }

}
