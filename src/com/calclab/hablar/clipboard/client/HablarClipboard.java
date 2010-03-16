package com.calclab.hablar.clipboard.client;

import com.calclab.hablar.chat.client.ui.ChatPage;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.google.gwt.core.client.EntryPoint;

public class HablarClipboard implements EntryPoint {

    public static final String ACTION_ID = "CopyToClipboardAction";

    public static void install(final Hablar hablar) {

	final CopyToClipboardPresenter copyToClipboardPage = new CopyToClipboardPresenter(hablar.getEventBus(),
		new CopyToClipboardWidget());
	hablar.addPage(copyToClipboardPage, OverlayContainer.ROL);

	final String actionName = "Copy to clipboard";
	final String actionIcon = HablarIcons.get(IconType.clipboard);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		if (event.isType(ChatPresenter.TYPE)) {
		    final ChatPage chatPage = (ChatPage) event.getPage();
		    chatPage.addAction(new SimpleAction<ChatPage>(actionName, ACTION_ID, actionIcon) {
			@Override
			public void execute(final ChatPage page) {
			    copyToClipboardPage.setChat(page);
			    copyToClipboardPage.requestVisibility(Visibility.focused);
			}
		    });
		}
	    }
	}, true);
    }

    @Override
    public void onModuleLoad() {
    }

}
