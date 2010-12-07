package com.calclab.hablar.chat.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.chat.client.ChatMessages;
import com.calclab.hablar.core.client.ui.actions.ActionWidget;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
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

    interface ActionItemStyle extends CssResource {
	String actionWidget();
    }

    interface ChatWidgetUiBinder extends UiBinder<Widget, ChatWidget> {
    }

    private static ChatMessages messages;

    private static final int CONTROLS_HEIGHT = 92;

    private static final int STATUS_HEIGHT = 24;

    private static ChatWidgetUiBinder uiBinder = GWT.create(ChatWidgetUiBinder.class);

    public static ChatMessages i18n() {
	return messages;
    }

    public static void setMessages(final ChatMessages messages) {
	ChatWidget.messages = messages;
    }

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

    public ChatWidget(final boolean sendButtonVisible) {
	initWidget(uiBinder.createAndBindUi(this));
	send.setText(i18n().sendAction());
	controlsHeight = 0;
    }

    @Override
    public void addMessage(final ChatMessage message, final String messageClass) {
	final ChatMessageWidget widget = new ChatMessageWidget(message);
	widget.addStyleName(messageClass);
	list.add(widget);
	scroll.ensureVisible(widget);
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
    public HasText getState() {
	return state;
    }

    @Override
    public HasKeyDownHandlers getTextBox() {
	return talkBox;
    }

    @Override
    public HasFocusHandlers getTextBoxFocus() {
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
	state.setVisible(visible);
    }

    @Override
    public void setTextBoxFocus(final boolean hasFocus) {
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		talkBox.setFocus(hasFocus);
	    }
	});
    }

    private void layoutControls() {
	page.setWidgetTopBottom(scroll, STATUS_HEIGHT + 3, PX, controlsHeight + STATUS_HEIGHT + 3, PX);
	page.setWidgetBottomHeight(state, controlsHeight, PX, STATUS_HEIGHT, PX);
	page.setWidgetBottomHeight(controls, 3, PX, controlsHeight + 3, PX);
    }
}
