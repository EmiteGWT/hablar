package com.calclab.hablar.clipboard.client;

import com.calclab.hablar.chat.client.ui.ChatPage;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.rooms.client.room.RoomPage;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;

public class HablarClipboard implements EntryPoint {

    public static final String ACTION_ID = "CopyToClipboardAction";

    private static ClipboardMessages messages;

    public static ClipboardMessages i18n() {
	return messages;
    }

    public static void install(final Hablar hablar) {

	final CopyToClipboardPresenter copyToClipboardPage = new CopyToClipboardPresenter(hablar.getEventBus(),
		new CopyToClipboardWidget());
	hablar.addPage(copyToClipboardPage, OverlayContainer.ROL);

	final String actionName = i18n().copyToClipboardAction();
	final ImageResource actionIcon = HablarIcons.getBundle().clipboardIcon();

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		if (event.isType(ChatPresenter.TYPE)) {
		    final ChatPage chatPage = (ChatPage) event.getPage();
		    chatPage.addAction(new SimpleAction<ChatPage>(actionName, ACTION_ID, actionIcon) {
			@Override
			public void execute(final ChatPage page) {
			    copyToClipboardPage.setChatMessagesProvider(page);
			    copyToClipboardPage.requestVisibility(Visibility.focused);
			}
		    });
		}
		else if (event.isType(RoomPresenter.TYPE)) {
		    final RoomPage roomPage = (RoomPage) event.getPage();
		    roomPage.addAction(new SimpleAction<RoomPage>(actionName, ACTION_ID, actionIcon) {
			@Override
			public void execute(final RoomPage page) {
			    copyToClipboardPage.setChatMessagesProvider(page);
			    copyToClipboardPage.requestVisibility(Visibility.focused);
			}
		    });
		}
	    }
	}, true);
    }

    public static void setMessages(final ClipboardMessages messages) {
	HablarClipboard.messages = messages;
    }

    @Override
    public void onModuleLoad() {
	ClipboardMessages messages = (ClipboardMessages) GWT.create(ClipboardMessages.class);
	HablarClipboard.setMessages(messages);
	CopyToClipboardWidget.setMessages(messages);
    }

}
