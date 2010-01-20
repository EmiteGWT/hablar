package com.calclab.hablar.basic.client.ui.page;

import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;

public class PageLogic {
    private final Event<PageView> statusAction;
    private final Event<PageView> closeEvent;
    private final Event<PageView> openEvent;
    private String status;
    private final PageView view;
    private Visibility visibility;

    public PageLogic(PageView view, Visibility visibility) {
	this.view = view;
	this.visibility = visibility;
	this.closeEvent = new Event<PageView>("page.close");
	this.statusAction = new Event<PageView>("page.status");
	this.openEvent = new Event<PageView>("page.open");
    }

    public void fireOpen() {
	openEvent.fire(view);
    }

    public String getStatusMessage() {
	return status;
    }

    public Visibility getVisibility() {
	return visibility;
    }

    public void onClose(Listener<PageView> listener) {
	closeEvent.add(listener);
    }

    public void onStatusMessageChanged(Listener<PageView> listener) {
	statusAction.add(listener);
    }

    public void onVisibilityChanged(Listener<PageView> listener) {
	openEvent.add(listener);
    }

    public void setStatusMessage(String status) {
	this.status = status;
	statusAction.fire(view);
	if (visibility != Visibility.open) {
	    view.getHeader().requestFocus();
	}
    }

    public void setVisibility(final Visibility visibility) {
	this.visibility = visibility;
	view.getHeader().setVisibility(visibility);
    }

    protected void fireClose() {
	closeEvent.fire(view);
    }

}
