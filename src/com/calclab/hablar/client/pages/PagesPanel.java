package com.calclab.hablar.client.pages;

import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A StackPanel with a status and more control over the current opened widgets.
 * Can add and remove PageWidgets. Can retrieve the current visible page and
 * fire events when a page is open or close
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

    private final Listener<String> statusListener;
    private final Listener<PageWidget> openListener;
    private PageWidget currentPage;

    public PagesPanel() {
	initWidget(uiBinder.createAndBindUi(this));
	statusListener = new Listener<String>() {
	    @Override
	    public void onEvent(String status) {
		GWT.log("Hablar status: " + status, null);
		setStatus(status);
	    }
	};
	openListener = new Listener<PageWidget>() {
	    @Override
	    public void onEvent(PageWidget page) {
		if (currentPage != null && page.isOpened()) {
		    currentPage.setOpen(false);
		    currentPage = page;
		}
	    }
	};
    }

    public PageWidget getCurrentPage() {
	return currentPage;
    }

    public void setStatus(String status) {
	this.status.setText(status);
    }

    public void add(PageWidget page, boolean visible) {
	page.onStatusChanged(statusListener);
	page.onOpenChanged(openListener);
	stack.add(page, page.getHeader(), 24);
	if (visible)
	    show(page);
    }

    public void show(PageWidget page) {
	page.setOpen(true);
	stack.showWidget(page);
    }

    public void remove(PageWidget page) {
	stack.remove(page);
    }

}
