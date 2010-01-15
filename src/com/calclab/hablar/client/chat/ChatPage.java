package com.calclab.hablar.client.chat;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.hablar.client.ui.page.PageWidget;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ChatPage extends PageWidget implements ChatView {

    interface ChatWidgetUiBinder extends UiBinder<Widget, ChatPage> {
    }

    private static ChatWidgetUiBinder uiBinder = GWT.create(ChatWidgetUiBinder.class);

    @UiField
    TextArea talkBox;

    @UiField
    LayoutPanel page;

    @UiField
    FlowPanel list;

    private final ChatLogic logic;

    public ChatPage(final Chat chat) {
	super(true);
	initWidget(uiBinder.createAndBindUi(this));
	logic = new ChatLogic(chat, this);
	setHeaderIconClass(HablarStyles.get(HablarStyles.IconType.buddy));
    }

    public void clearAndFocus() {
	talkBox.setText("");
	talkBox.setFocus(true);
    }

    @UiHandler("talkBox")
    public void handleKeys(final KeyDownEvent event) {
	if (event.getNativeKeyCode() == 13) {
	    final String message = talkBox.getText();
	    logic.onTalk(message);
	    event.stopPropagation();
	    event.preventDefault();
	}
    }

    @Override
    public void setPresence(Show show) {
	logic.setPresence(show);
    }

    @Override
    public void setTextBoxVisible(boolean visible) {
	if (visible) {
	    page.setWidgetTopBottom(list, 0, PX, 64, PX);
	    page.setWidgetBottomHeight(talkBox, 0, PX, 61, PX);
	} else {
	    page.setWidgetTopBottom(list, 0, PX, 0, PX);
	    page.setWidgetBottomHeight(talkBox, 0, PX, 0, PX);
	}
	page.animate(500);
    }

    public void showMessage(final String name, final String body, final MessageType type) {
	list.add(new ChatMessage(name, body, type));
    }

}
