package com.calclab.hablar.basic.client.ui.page;

import com.calclab.hablar.basic.client.ui.EventBus;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.page.events.ClosePageEvent;
import com.calclab.hablar.basic.client.ui.page.events.OpenPageEvent;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageEvent;

public class PageLogic {
    private final PageHeader header;
    private String status;
    private Visibility visibility;
    private final EventBus eventBus;
    private final PageView view;

    public PageLogic(EventBus eventBus, PageView view, PageHeader header, Visibility visibility) {
	this.eventBus = eventBus;
	this.view = view;
	this.header = header;
	this.visibility = visibility;
	header.setLogic(this);
    }

    public void fireOpen() {
	eventBus.fireEvent(new OpenPageEvent(this));

    }

    public String getStatusMessage() {
	return status;
    }

    public PageView getView() {
	return view;
    }

    public Visibility getVisibility() {
	return visibility;
    }

    public void setStatusMessage(String status) {
	this.status = status;
	eventBus.fireEvent(new UserMessageEvent(status, this));
	if (visibility != Visibility.focused) {
	    header.requestFocus();
	}
    }

    public void setVisibility(final Visibility visibility) {
	this.visibility = visibility;
	header.setVisibility(visibility);
    }

    protected void fireClose() {
	eventBus.fireEvent(new ClosePageEvent(this));
    }

}
