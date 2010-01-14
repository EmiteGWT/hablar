package com.calclab.hablar.client.ui.page;

import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.user.client.ui.Composite;

/**
 * A PageWidget is a widget with a header and some status. Is the base of the
 * hablar widgets panels.
 */
public abstract class PageWidget extends Composite implements Page {

    protected final PageHeader header;
    private final Event<PageWidget> statusAction;
    private final Event<PageWidget> closeEvent;
    private final Event<PageWidget> openEvent;
    private Visibility visibility;
    private String status;

    public PageWidget(boolean closeable) {
	this.closeEvent = new Event<PageWidget>("page.close");
	this.header = new PageHeader(this, closeable);
	this.statusAction = new Event<PageWidget>("page.status");
	this.openEvent = new Event<PageWidget>("page.open");
	this.visibility = Visibility.hidden;
    }

    public void fireOpen() {
	openEvent.fire(this);
    }

    public HeaderView getHeader() {
	return this.header;
    }

    public String getStatus() {
	return status;
    }

    public Visibility getVisibility() {
	return visibility;
    }

    public void onClose(Listener<PageWidget> listener) {
	closeEvent.add(listener);
    }

    public void onStatusChanged(Listener<PageWidget> listener) {
	statusAction.add(listener);
    }

    public void onVisibilityChanged(Listener<PageWidget> listener) {
	openEvent.add(listener);
    }

    public void setHeaderIconClass(String iconClass) {
	header.setIconClass(iconClass);
    }

    public void setHeaderTitle(String title) {
	header.setHeaderTitle(title);
    }

    public void setStatus(String status) {
	this.status = status;
	statusAction.fire(this);
	if (visibility == Visibility.closed)
	    header.requestFocus();
    }

    public void setVisibility(Visibility visibility) {
	this.visibility = visibility;
	header.setVisibility(visibility);
    }

    protected void fireClose() {
	closeEvent.fire(this);
    }

}
