package com.calclab.hablar.signals.client;

import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;

public class HablarSignals implements EntryPoint {

    public static void install(final HablarWidget hablar) {
	HasText titleDisplay = new HasText() {
	    @Override
	    public String getText() {
		return Window.getTitle();
	    }

	    @Override
	    public void setText(String text) {
		Window.setTitle(text);
	    }
	};

	HablarEventBus hablarEventBus = hablar.getHablarEventBus();
	new UnattendedChatPages(hablarEventBus);
	new WindowTitlePresenter(hablarEventBus, titleDisplay);

    }

    @Override
    public void onModuleLoad() {
    }

}
