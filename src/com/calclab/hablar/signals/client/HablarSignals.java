package com.calclab.hablar.signals.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;

public class HablarSignals implements EntryPoint {

    public static void install(Hablar hablarPresenter) {
	HablarEventBus eventBus = hablarPresenter.getEventBus();
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

	new UnattendedChatPages(eventBus);
	new WindowTitlePresenter(eventBus, titleDisplay);
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    @Override
    public void onModuleLoad() {
    }

}
