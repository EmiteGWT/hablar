package com.calclab.hablar.client.pages;

import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Event0;
import com.calclab.suco.client.events.Listener;
import com.calclab.suco.client.events.Listener0;
import com.google.gwt.user.client.ui.Composite;

/**
 * A PageWidget is a widget with a header and some status. Is the base of the
 * hablar widgets panels.
 */
public abstract class PageWidget extends Composite {

    protected final PageHeader header;
    private final Event<String> statusAction;
    private final Event0 closeEvent;
    private final Event<PageWidget> openEvent;
    private boolean open;

    public PageWidget(boolean closeable) {
	this.closeEvent = new Event0("page.close");
	this.header = new PageHeader(this, closeable);
	this.statusAction = new Event<String>("page.status");
	this.openEvent = new Event<PageWidget>("page.open");
	this.open = false;
    }

    public void close() {
	closeEvent.fire();
    }

    public PageHeader getHeader() {
	return this.header;
    }

    public boolean isOpened() {
	return open;
    }

    public void onClose(Listener0 listener) {
	closeEvent.add(listener);
    }

    public void onOpenChanged(Listener<PageWidget> listener) {
	openEvent.add(listener);
    }

    public void onStatusChanged(Listener<String> listener) {
	statusAction.add(listener);
    }

    public void setHeaderIconClass(String iconClass) {
	header.setIconClass(iconClass);
    }

    public void setHeaderStyles(HeaderStyles styles) {
	header.setStyles(styles);
    }

    public void setHeaderTitle(String title) {
	header.setHeaderTitle(title);
    }

    public void setOpen(boolean open) {
	this.open = open;
	header.setOpen(open);
	openEvent.fire(this);
    }

    public void setStatus(String status) {
	statusAction.fire(status);
	if (!open)
	    header.setActive(true);
    }

}
