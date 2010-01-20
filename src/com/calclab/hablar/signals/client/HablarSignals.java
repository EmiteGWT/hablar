package com.calclab.hablar.signals.client;

import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.chat.client.ChatPage;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarSignals implements EntryPoint {

    public static void install(final HablarWidget hablar) {
	hablar.getPages().onStatusMessageChanged(new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView page) {
		final String pageType = page.getPageType();
		if (pageType == ChatPage.TYPE) {
		    GWT.log("GROWL: " + page.getStatusMessage(), null);
		    final ChatPage chatPage = (ChatPage) page;
		    chatPage.getChat().getURI();
		}
	    }
	});

	hablar.getPages().onPageClosed(new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView parameter) {
	    }
	});

	hablar.getPages().onPageOpened(new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView parameter) {
	    }
	});
    }

    @Override
    public void onModuleLoad() {
    }

}
