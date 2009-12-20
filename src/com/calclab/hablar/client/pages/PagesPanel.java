package com.calclab.hablar.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A StackPanel with a status. Can add PageWidgets.
 * 
 * @author dani
 * 
 */
public class PagesPanel extends Composite {

    private static HablarWidgetUiBinder uiBinder = GWT.create(HablarWidgetUiBinder.class);

    interface HablarWidgetUiBinder extends UiBinder<Widget, PagesPanel> {
    }

    @UiField
    Label status;
    @UiField
    StackLayoutPanel stack;

    public PagesPanel() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    public void setStatus(String status) {
	this.status.setText(status);
    }

    public void add(PageWidget widget, boolean visible) {
	stack.add(widget, widget.getHeader(), 24);
	if (visible)
	    stack.showWidget(widget);
    }

    public void show(Widget widget) {
	stack.showWidget(widget);
    }

    public void remove(PageWidget panel) {
	stack.remove(panel);
    }

}
