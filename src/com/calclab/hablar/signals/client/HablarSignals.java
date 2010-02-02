package com.calclab.hablar.signals.client;

import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.hablar.chat.client.ui.ChatPageWidget;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;

public class HablarSignals implements EntryPoint {

    public static void install(final HablarWidget hablar) {

	final ChatSignalsLogic chatSignalsLogic = new ChatSignalsLogic();
	new WindowTitleManager(chatSignalsLogic);

	final Listener<PageView> statusMessageChgListener = new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView chatPage) {
		chatSignalsLogic.onNewMsg(chatPage);
	    }
	};

	final Listener<PageView> onPageOpened = new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView chatPage) {
		chatSignalsLogic.onChatOpened(chatPage);
	    }
	};

	final Listener<PageView> onPageClose = new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView chatPage) {
		chatSignalsLogic.onChatClosed(chatPage);
	    }
	};

	final Pages pages = hablar.getPages();
	pages.onStatusMessageChanged(new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView page) {
		ifChat(page, statusMessageChgListener);
	    }
	});

	pages.onPageClosed(new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView page) {
		ifChat(page, onPageClose);
	    }
	});

	pages.onPageOpened(new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView page) {
		ifChat(page, onPageOpened);
	    }
	});
    }

    protected static void ifChat(final PageView page, final Listener<PageView> listener) {
	final String pageType = page.getPageType();
	if (pageType == ChatPageWidget.TYPE) {
	    listener.onEvent(page);
	}
    }

    @Override
    public void onModuleLoad() {
    }

}
