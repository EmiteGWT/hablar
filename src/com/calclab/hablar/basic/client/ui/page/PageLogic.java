package com.calclab.hablar.basic.client.ui.page;

import com.calclab.hablar.basic.client.ui.EventBus;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.page.events.ClosePageEvent;
import com.calclab.hablar.basic.client.ui.page.events.OpenPageEvent;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class PageLogic {
    private String status;
    private Visibility visibility;
    private final EventBus eventBus;
    private final PageView view;

    public PageLogic(EventBus eventBus, PageView view, Visibility visibility) {
	this.eventBus = eventBus;
	this.view = view;
	this.visibility = visibility;
	bind(view.getHeader());
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
	    view.getHeader().requestFocus();
	}
    }

    public void setVisibility(final Visibility visibility) {
	this.visibility = visibility;
	view.getHeader().setVisibility(visibility);
    }

    private void bind(PageHeader header) {
	header.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		eventBus.fireEvent(new OpenPageEvent(PageLogic.this));
	    }
	});
	header.getCloseIcon().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		eventBus.fireEvent(new ClosePageEvent(PageLogic.this));
	    }
	});
    }

}
