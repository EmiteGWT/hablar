package com.calclab.hablar.chat.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PX;

import java.util.ArrayList;

import com.calclab.hablar.chat.client.ChatMessages;
import com.calclab.hablar.core.client.ui.actions.ActionWidget;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ChatWidget extends Composite implements ChatDisplay {

    private static ChatMessages messages;

    public static void setMessages(final ChatMessages messages) {
	ChatWidget.messages = messages;
    }

    public static ChatMessages i18n() {
	return messages;
    }

    interface ActionItemStyle extends CssResource {
	String actionWidget();
    }

    interface ChatWidgetUiBinder extends UiBinder<Widget, ChatWidget> {
    }

    private static final int CONTROLS_HEIGHT = 115;
    private static final int STATUS_HEIGHT = 24;

    private static ChatWidgetUiBinder uiBinder = GWT.create(ChatWidgetUiBinder.class);

    @UiField
    protected TextArea talkBox;
    @UiField
    protected LayoutPanel page;
    @UiField
    protected FlowPanel list, controls, actions;
    @UiField
    protected ScrollPanel scroll;
    @UiField
    protected Button send;
    @UiField
    protected Label state;

    @UiField
    ActionItemStyle style;

    private int controlsHeight;
    private int statusHeight;

    public ChatWidget(final boolean sendButtonVisible) {
	initWidget(uiBinder.createAndBindUi(this));
	send.setText(i18n().sendAction());
	controlsHeight = 0;
	statusHeight = 0;
    }

    @Override
    public ChatMessageDisplay addMessage(final String name, final Element body, final ChatDisplay.MessageType messageType) {
	final ChatMessage message = new ChatMessage(name, body, messageType);
	list.add(message);
	scroll.ensureVisible(message);
	return message;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void addToActions(final Widget widget) {
	actions.add(widget);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clearAndFocus() {
	talkBox.setText("");
	talkBox.setFocus(true);
    }

    @Override
    public HasClickHandlers createAction(final Action<?> action) {
	final ActionWidget actionWidget = new ActionWidget(action);
	actionWidget.addStyleName(style.actionWidget());
	actions.add(actionWidget);
	return actionWidget;
    }

    @Override
    public HasClickHandlers getAction() {
	return send;
    }

    @Override
    public HasText getBody() {
	return talkBox;
    }

    @Override
    public ArrayList<ChatMessageDisplay> getMessages() {
	final ArrayList<ChatMessageDisplay> messageList = new ArrayList<ChatMessageDisplay>();
	final int widgetCount = list.getWidgetCount();
	for (int index = 0; index < widgetCount; index++) {
	    messageList.add((ChatMessageDisplay) list.getWidget(index));
	}
	return messageList;
    }

    @Override
    public HasText getState() {
	return state;
    }

    @Override
    public HasKeyDownHandlers getTextBox() {
	return talkBox;
    }

    @Override
    public void setControlsVisible(final boolean visible) {
	layoutControls();
	// FIXME: Animation
	// page.forceLayout();
	controlsHeight = visible ? CONTROLS_HEIGHT : 0;
	layoutControls();
	page.animate(500);
    }

    @Override
    public void setId(final String id) {
	page.ensureDebugId("ChatWidget-" + id);
	talkBox.ensureDebugId("ChatWidget-talkBox-" + id);
	send.ensureDebugId("ChatWidget-send-" + id);
	list.ensureDebugId("ChatWidget-list-" + id);
	scroll.ensureDebugId("ChatWidget-scroll-" + id);
	actions.ensureDebugId("ChatWidget-status-" + id);

    }

    @Override
    public void setStatusVisible(final boolean visible) {
	layoutStatus();
	// FIXME: Animation
	// page.forceLayout();
	statusHeight = visible ? STATUS_HEIGHT : 0;
	layoutStatus();
	page.animate(500);
    }

    private void layoutControls() {
	page.setWidgetTopBottom(scroll, statusHeight + 3, PX, controlsHeight + 3, PX);
	page.setWidgetBottomHeight(controls, 3, PX, controlsHeight, PX);
    }

    private void layoutStatus() {
	page.setWidgetTopBottom(scroll, statusHeight + 3, PX, controlsHeight + 3, PX);
	page.setWidgetTopHeight(actions, 3, PX, statusHeight, PX);

    }

}
