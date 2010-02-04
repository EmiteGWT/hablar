package com.calclab.hablar.core.client.page;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.FocusPageRequestEvent;
import com.calclab.hablar.core.client.page.events.HidePageRequestEvent;

public class PagePresenter<T extends Display> implements Page<T> {
    public static enum Visibility {
	focused, notFocused, hidden
    }
    protected final T display;
    protected final HablarEventBus eventBus;
    private final String pageType;
    private final String id;

    protected final PageState model;

    public PagePresenter(String pageType, String id, HablarEventBus eventBus, T display) {
	this.pageType = pageType;
	this.eventBus = eventBus;
	this.display = display;
	this.id = pageType + "-" + id;
	model = new PageState(eventBus, this);
    }

    @Override
    public T getDisplay() {
	return display;
    }

    public String getId() {
	return id;
    }

    @Override
    public PageState getState() {
	return model;
    }

    public String getType() {
	return pageType;
    }

    @Override
    public void requestHide() {
	eventBus.fireEvent(new HidePageRequestEvent(this));
    }

    public void requestOpen() {
	eventBus.fireEvent(new FocusPageRequestEvent(this));
    }

    @Override
    public void setVisibility(Visibility visibility) {
	model.setVisibility(visibility);
    }

}
