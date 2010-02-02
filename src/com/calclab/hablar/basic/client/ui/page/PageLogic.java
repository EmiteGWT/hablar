package com.calclab.hablar.basic.client.ui.page;

import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.page.events.ClosePageEvent;
import com.calclab.hablar.basic.client.ui.page.events.OpenPageEvent;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class PageLogic {
    private String status;
    private Visibility visibility;
    private final HablarEventBus hablarEventBus;
    private final PageView view;

    public PageLogic(HablarEventBus hablarEventBus, PageView view, Visibility visibility) {
	this.hablarEventBus = hablarEventBus;
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
	hablarEventBus.fireEvent(new UserMessageEvent(status, this));
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
		hablarEventBus.fireEvent(new OpenPageEvent(PageLogic.this));
	    }
	});
	header.getCloseIcon().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		hablarEventBus.fireEvent(new ClosePageEvent(PageLogic.this));
	    }
	});
    }

}
