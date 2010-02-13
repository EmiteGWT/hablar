package com.calclab.hablar.logger.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LoggerWidget extends Composite implements LoggerDisplay {

    interface LoggerWidgetUiBinder extends UiBinder<Widget, LoggerWidget> {
    }

    private static LoggerWidgetUiBinder uiBinder = GWT.create(LoggerWidgetUiBinder.class);
    @UiField
    FlowPanel output;
    @UiField
    Anchor clear;

    public LoggerWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void add(final String message, final String styleName, final String sessionStyle) {
	final Label label = new Label(message);
	label.addStyleName(styleName);
	label.addStyleName(sessionStyle);
	output.add(label);
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

}
