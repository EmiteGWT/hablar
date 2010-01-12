package com.calclab.hablar.client.chat;

import com.calclab.emite.im.client.chat.Chat;
import com.calclab.hablar.client.pages.PageWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ChatPage extends PageWidget {

    interface ChatWidgetUiBinder extends UiBinder<Widget, ChatPage> {
    }

    interface Icons extends CssResource {

	String chatIcon();
    }

    enum MessageType {
	incoming, sent
    }

    private static ChatWidgetUiBinder uiBinder = GWT.create(ChatWidgetUiBinder.class);

    @UiField
    TextArea talkBox;

    @UiField
    Icons icons;

    @UiField
    FlowPanel list;

    private final ChatLogic logic;

    public ChatPage(Chat chat) {
	super(true);
	initWidget(uiBinder.createAndBindUi(this));
	logic = new ChatLogic(chat, this);
	setHeaderIconClass(icons.chatIcon());
    }

    public void clearAndFocus() {
	talkBox.setText("");
	talkBox.setFocus(true);
    }

    @UiHandler("talkBox")
    public void handleKeys(KeyDownEvent event) {
	if (event.getNativeKeyCode() == 13) {
	    logic.onTalk(talkBox.getText());
	    event.stopPropagation();
	    event.preventDefault();
	}
    }

    public void showMessage(String name, String body, MessageType type) {
	list.add(new ChatMessage(name, body, type));
    }

}
