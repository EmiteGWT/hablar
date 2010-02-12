package com.calclab.hablar.chat.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.ui.menu.Action;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
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

    interface ChatWidgetUiBinder extends UiBinder<Widget, ChatWidget> {
    }

    private static ChatWidgetUiBinder uiBinder = GWT.create(ChatWidgetUiBinder.class);

    @UiField
    TextArea talkBox;
    @UiField
    LayoutPanel page;
    @UiField
    FlowPanel list, controls, actions;
    @UiField
    ScrollPanel scroll;
    @UiField
    Button send;

    private final int controlsHeight;

    public ChatWidget(final boolean sendButtonVisible) {
	initWidget(uiBinder.createAndBindUi(this));
	controlsHeight = sendButtonVisible ? 64 + 30 : 64;
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
	final Label label = new Label();
	label.addStyleName(action.getIconStyle());
	actions.add(label);
	return label;
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
    public HasKeyDownHandlers getTextBox() {
	return talkBox;
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
    public void setId(final String id) {
	page.ensureDebugId("ChatWidget-" + id);
	talkBox.ensureDebugId("ChatWidget-talkBox-" + id);
	send.ensureDebugId("ChatWidget-send-" + id);
	list.ensureDebugId("ChatWidget-list-" + id);
	scroll.ensureDebugId("ChatWidget-srcoll-" + id);
    }

    @Override
    public void showMessage(final String name, final String body, final ChatDisplay.MessageType messageType) {
	final ChatMessage message = new ChatMessage(name, body, messageType);
	list.add(message);
	scroll.ensureVisible(message);
    }

}
