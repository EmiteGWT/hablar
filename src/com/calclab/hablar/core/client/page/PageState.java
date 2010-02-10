package com.calclab.hablar.core.client.page;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.UserMessageChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedHandler;

public class PageState {
    private String pageTitle, userMessage, pageIcon;
    private boolean closeable;
    private Visibility visibility;
    private final HablarEventBus eventBus;
    private final Page<?> page;

    public PageState(HablarEventBus eventBus, Page<?> page) {
	this.eventBus = eventBus;
	this.page = page;
	visibility = Visibility.hidden;
	closeable = false;
    }

    public void addInfoChangedHandler(final PageInfoChangedHandler handler) {
	eventBus.addHandler(PageInfoChangedEvent.TYPE, new PageInfoChangedHandler() {
	    @Override
	    public void onPageInfoChanged(PageInfoChangedEvent event) {
		if (event.getPage() == page) {
		    handler.onPageInfoChanged(event);
		}
	    }
	});
    }

    public void addVisibilityChangedHandler(final VisibilityChangedHandler handler) {
	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		if (event.getPage() == page) {
		    handler.onVisibilityChanged(event);
		}
	    }
	});
    }

    public Page<?> getPage() {
	return page;
    }

    public String getPageIcon() {
	return pageIcon;
    }

    public String getPageTitle() {
	return pageTitle;
    }

    public String getUserMessage() {
	return userMessage;
    }

    public Visibility getVisibility() {
	return visibility;
    }

    public void init(String style, String title) {
	pageIcon = style;
	pageTitle = title;
	fireChanged();
    }

    public boolean isCloseable() {
	return closeable;
    }

    public void setCloseable(boolean closeable) {
	this.closeable = closeable;
	fireChanged();
    }

    public void setPageIcon(String pageIcon) {
	this.pageIcon = pageIcon;
	fireChanged();

    }

    public void setPageTitle(String pageTitle) {
	this.pageTitle = pageTitle;
	fireChanged();
    }

    public void setUserMessage(String userMessage) {
	this.userMessage = userMessage;
	eventBus.fireEvent(new UserMessageChangedEvent(page));
    }

    public void setVisibility(Visibility visibility) {
	this.visibility = visibility;
	eventBus.fireEvent(new VisibilityChangedEvent(page, getVisibility()));
    }

    private void fireChanged() {
	eventBus.fireEvent(new PageInfoChangedEvent(page, this));
    }

}
