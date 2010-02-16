package com.calclab.hablar.core.client.page;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.UserMessageChangedEvent;
import com.calclab.hablar.core.client.page.events.UserMessageChangedHandler;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedHandler;

public class PageState {
    private String pageTitle, userMessage, pageIcon, pageTitleTooltip;
    private boolean closeable;
    private Visibility visibility;
    private final HablarEventBus eventBus;
    private final Page<?> page;

    public PageState(final HablarEventBus eventBus, final Page<?> page) {
	this.eventBus = eventBus;
	this.page = page;
	visibility = Visibility.hidden;
	closeable = false;
    }

    public void addInfoChangedHandler(final PageInfoChangedHandler handler) {
	eventBus.addHandler(PageInfoChangedEvent.TYPE, new PageInfoChangedHandler() {
	    @Override
	    public void onPageInfoChanged(final PageInfoChangedEvent event) {
		if (event.getPage() == page) {
		    handler.onPageInfoChanged(event);
		}
	    }
	});
    }

    public void addUserMessageChangedHandler(final UserMessageChangedHandler handler) {
	eventBus.addHandler(UserMessageChangedEvent.TYPE, new UserMessageChangedHandler() {
	    @Override
	    public void onUserMessageChanged(final UserMessageChangedEvent event) {
		if (event.getPageState() == PageState.this) {
		    handler.onUserMessageChanged(event);
		}
	    }
	});
    }

    public void addVisibilityChangedHandler(final VisibilityChangedHandler handler) {
	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(final VisibilityChangedEvent event) {
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

    public String getPageTitleTooltip() {
	return pageTitleTooltip;
    }

    public String getUserMessage() {
	return userMessage;
    }

    public Visibility getVisibility() {
	return visibility;
    }

    public void init(final String style, final String title) {
	pageIcon = style;
	pageTitle = title;
	fireChanged();
    }

    public boolean isCloseable() {
	return closeable;
    }

    public void setCloseable(final boolean closeable) {
	this.closeable = closeable;
	fireChanged();
    }

    public void setPageIcon(final String pageIcon) {
	this.pageIcon = pageIcon;
	fireChanged();

    }

    public void setPageTitle(final String pageTitle) {
	this.pageTitle = pageTitle;
	fireChanged();
    }

    public void setPageTitleTooltip(final String pageTitleTooltip) {
	this.pageTitleTooltip = pageTitleTooltip;
	fireChanged();
    }

    public void setUserMessage(final String userMessage) {
	if (userMessage != null && !userMessage.equals(this.userMessage)) {
	    this.userMessage = userMessage;
	    eventBus.fireEvent(new UserMessageChangedEvent(page, userMessage));
	}
    }

    public void setVisibility(final Visibility visibility) {
	this.visibility = visibility;
	eventBus.fireEvent(new VisibilityChangedEvent(page, getVisibility()));
    }

    private void fireChanged() {
	eventBus.fireEvent(new PageInfoChangedEvent(page, this));
    }

}
