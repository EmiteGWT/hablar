package com.calclab.hablar.signals.client;

import com.calclab.hablar.basic.client.ui.EventBus;
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

	EventBus eventBus = hablar.getEventBus();
	new UnattendedChatPages(eventBus);
	new WindowTitlePresenter(eventBus, titleDisplay);

    }

    @Override
    public void onModuleLoad() {
    }

}
