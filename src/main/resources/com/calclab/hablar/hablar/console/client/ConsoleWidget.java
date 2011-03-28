package com.calclab.hablar.console.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ConsoleWidget extends Composite implements ConsoleDisplay {

    interface LoggerWidgetUiBinder extends UiBinder<Widget, ConsoleWidget> {
    }

    private static LoggerWidgetUiBinder uiBinder = GWT.create(LoggerWidgetUiBinder.class);
    @UiField
    FlowPanel output;
    @UiField
    Anchor clear;
    @UiField
    TextArea input;
    @UiField
    ScrollPanel scroll;

    public ConsoleWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void add(final String message, final String styleName, final String sessionStyle) {
	final Label label = new Label(message);
	label.addStyleName(styleName);
	label.addStyleName(sessionStyle);
	label.addStyleName("hablar-clearBackground");
	label.addStyleName("hablar-bottomLightBordered");
	output.add(label);
	scroll.ensureVisible(label);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clear() {
	output.clear();
    }

    @Override
    public HasClickHandlers getClear() {
	return clear;
    }

    @Override
    public HasKeyDownHandlers getInput() {
	return input;
    }

    @Override
    public HasText getInputText() {
	return input;
    }

    @Override
    public void setInputCursorPos(final int pos) {
	input.setCursorPos(pos);
    }
}
