package com.calclab.hablar.logger.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LoggerWidget extends Composite implements LoggerDisplay {

    private static LoggerWidgetUiBinder uiBinder = GWT.create(LoggerWidgetUiBinder.class);

    interface LoggerWidgetUiBinder extends UiBinder<Widget, LoggerWidget> {
    }
    @UiField
    FlowPanel output;

    public LoggerWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void add(String message, String styleName, String sessionStyle) {
	Label label = new Label(message);
	label.addStyleName(styleName);
	label.addStyleName(sessionStyle);
	output.add(label);
    }

}
