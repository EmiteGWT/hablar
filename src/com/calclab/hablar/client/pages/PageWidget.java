package com.calclab.hablar.client.pages;

import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Event0;
import com.calclab.suco.client.events.Listener;
import com.calclab.suco.client.events.Listener0;
import com.google.gwt.user.client.ui.Composite;

/**
 * A PageWidget is a widget with a header
 */
public abstract class PageWidget extends Composite {

    protected final PageHeader header;
    private final Event<String> statusAction;
    private final Event0 closeEvent;

    public PageWidget(boolean closeable) {
	this.closeEvent = new Event0("page.close");
	this.header = new PageHeader(this, closeable);
	this.statusAction = new Event<String>("page.status");
    }
    
    public void setStatus(String status) {
	statusAction.fire(status);
	header.setActive(true);
    }
    
    public void onStatusChanged(Listener<String> listener) {
	statusAction.add(listener);
    }
    
    public void setHeaderTitle(String title) {
	header.setHeaderTitle(title);
    }

    public PageHeader getHeader() {
	return this.header;
    }
    
    public void onClose(Listener0 listener) {
	closeEvent.add(listener);
    }

    public void close() {
	closeEvent.fire();
    }

}

