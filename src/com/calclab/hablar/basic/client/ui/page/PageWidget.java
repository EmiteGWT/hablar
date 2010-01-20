package com.calclab.hablar.basic.client.ui.page;

import com.calclab.suco.client.events.Listener;
import com.google.gwt.user.client.ui.Composite;

/**
 * A PageWidget is a widget with a header and some status. Is the base of the
 * hablar widgets panels.
 */
public abstract class PageWidget extends Composite implements PageView {

    protected final HeaderWidget header;
    private final PageLogic logic;
    private final String pageType;

    public PageWidget(String pageType, Visibility visibility, final boolean closeable) {
	this.pageType = pageType;
	this.header = new HeaderWidget(closeable);
	logic = new PageLogic(this, header, visibility);
    }

    public void fireOpen() {
	logic.fireOpen();
    }

    public PageHeader getHeader() {
	return this.header;
    };

    @Override
    public String getPageType() {
	return pageType;
    }

    @Override
    public String getStatusMessage() {
	return logic.getStatusMessage();
    }

    @Override
    public Visibility getVisibility() {
	return logic.getVisibility();
    }

    public void onClose(final Listener<PageView> listener) {
	logic.onClose(listener);
    }

    public void onStatusMessageChanged(final Listener<PageView> listener) {
	logic.onStatusMessageChanged(listener);
    }

    public void onVisibilityChanged(final Listener<PageView> listener) {
	logic.onVisibilityChanged(listener);
    }

    @Override
    public void setHeaderIconClass(final String iconClass) {
	header.setIconClass(iconClass);
    }

    @Override
    public void setHeaderTitle(final String title) {
	header.setHeaderTitle(title);
    }

    public void setId(final String id) {
	header.setId(id);
    }

    @Override
    public void setStatusMessage(final String status) {
	logic.setStatusMessage(status);
    }

    @Override
    public void setVisibility(Visibility visibility) {
	logic.setVisibility(visibility);
    }

}
