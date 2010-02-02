package com.calclab.hablar.chat.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.hablar.basic.client.ui.EventBus;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.page.PageWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ChatPageWidget extends PageWidget implements ChatPageView {

    interface ChatWidgetUiBinder extends UiBinder<Widget, ChatPageWidget> {
    }

    public static final String ID = "ChatPage";
    public static final String TALKBOX_DEB_ID = "ChatPage-talkBox";
    public static final String LIST_DEB_ID = "ChatPage-list";
    public static final String TYPE = "Chat";

    private static ChatWidgetUiBinder uiBinder = GWT.create(ChatWidgetUiBinder.class);

    @UiField
    TextArea talkBox;
    @UiField
    LayoutPanel page;
    @UiField
    FlowPanel list, controls;
    @UiField
    ScrollPanel scroll;
    @UiField
    Button send;

    private final ChatPageLogic logic;
    private final Chat chat;
    private final int controlsHeight;

    public ChatPageWidget(EventBus eventBus, final Chat chat, Visibility visibility, boolean sendButtonVisible) {
	super(eventBus, TYPE, visibility, true);
	this.chat = chat;
	super.setId(ID);
	initWidget(uiBinder.createAndBindUi(this));
	talkBox.ensureDebugId(TALKBOX_DEB_ID);
	list.ensureDebugId(LIST_DEB_ID);
	logic = new ChatPageLogic(chat, this);
	setHeaderIconClass(HablarIcons.get(HablarIcons.IconType.buddy));
	controlsHeight = sendButtonVisible ? 64 + 30 : 64;
    }

    public void clearAndFocus() {
	talkBox.setText("");
	talkBox.setFocus(true);
    }

    public Chat getChat() {
	return chat;
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

    @UiHandler("send")
    public void handleSend(ClickEvent event) {
	logic.onTalk(talkBox.getText());
    }

    @Override
    public void setControlsVisible(final boolean visible) {
	if (visible) {
	    page.setWidgetTopBottom(scroll, 0, PX, controlsHeight, PX);
	    page.setWidgetBottomHeight(controls, 0, PX, controlsHeight - 3, PX);
	} else {
	    page.setWidgetTopBottom(scroll, 0, PX, 0, PX);
	    page.setWidgetBottomHeight(controls, 0, PX, 0, PX);
	}
	page.animate(500);
    }

    @Override
    public void setPresence(final Show show) {
	logic.setPresence(show);
    }

    public void showMessage(final String name, final String body, final MessageType type) {
	final ChatMessage message = new ChatMessage(name, body, type);
	list.add(message);
	scroll.ensureVisible(message);
    }

}
