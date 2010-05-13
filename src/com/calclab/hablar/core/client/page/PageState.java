package com.calclab.hablar.core.client.page;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedHandler;
import com.google.gwt.resources.client.ImageResource;

public class PageState {
    private String pageTitle, pageTitleTooltip;
    private ImageResource pageIcon;
    private boolean closeable;
    private Visibility visibility;
    private final HablarEventBus eventBus;
    private final Page<?> page;
    private String externalState;

    public PageState(final HablarEventBus eventBus, final Page<?> page) {
	this.eventBus = eventBus;
	this.page = page;
	visibility = Visibility.hidden;
	closeable = false;
	externalState = null;
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

    public String getExternalState() {
	return externalState;
    }

    public Page<?> getPage() {
	return page;
    }

    public ImageResource getPageIcon() {
	return pageIcon;
    }

    public String getPageTitle() {
	return pageTitle;
    }

    public String getPageTitleTooltip() {
	return pageTitleTooltip;
    }

    public Visibility getVisibility() {
	return visibility;
    }

    public void init(ImageResource pageIcon, final String title, final String tooltip) {
	this.pageIcon = pageIcon;
	pageTitle = title;
	pageTitleTooltip = tooltip;
	fireChanged();
    }

    public boolean isCloseable() {
	return closeable;
    }

    public void setCloseable(final boolean closeable) {
	this.closeable = closeable;
	fireChanged();
    }

    public void setExternalState(final String externalState) {
	this.externalState = externalState;
	fireChanged();
    }

    public void setPageIcon(final ImageResource pageIcon) {
	this.pageIcon = pageIcon;
	fireChanged();
    }

    public void setPageTitle(final String pageTitle) {
	this.pageTitle = pageTitle;
	if (pageTitleTooltip == null) {
	    pageTitleTooltip = pageTitle;
	}
	fireChanged();
    }

    public void setPageTitleTooltip(final String pageTitleTooltip) {
	this.pageTitleTooltip = pageTitleTooltip;
	fireChanged();
    }

    public void setVisibility(final Visibility visibility) {
	this.visibility = visibility;
	eventBus.fireEvent(new VisibilityChangedEvent(page, getVisibility()));
    }

    private void fireChanged() {
	eventBus.fireEvent(new PageInfoChangedEvent(page, this));
    }

}
