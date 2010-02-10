package com.calclab.hablar.core.client.container.overlay;

import java.util.ArrayList;

import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestHandler;
import com.google.gwt.user.client.ui.Widget;

public class OverlayContainer implements PagesContainer {
    public static final String ROL = "Overlay";
    private Page<?> currentPagePresenter;
    private final ArrayList<Page<?>> pages;
    private final OverlayLayout layout;

    public OverlayContainer(HablarEventBus eventBus, OverlayLayout layout) {
	this.layout = layout;
	this.pages = new ArrayList<Page<?>>();
	layout.setVisible(false);
	eventBus.addHandler(VisibilityChangeRequestEvent.TYPE, new VisibilityChangeRequestHandler() {
	    @Override
	    public void onVisibilityChangeRequest(VisibilityChangeRequestEvent event) {
		changeVisibility(event.getNewVisibility(), event.getPage());
	    }
	});
    }

    @Override
    public boolean add(Page<?> page) {
	pages.add(page);
	if (page.getVisibility() == Visibility.focused) {
	    focus(page);
	}
	return true;
    }

    @Override
    public String getRol() {
	return ROL;
    }

    @Override
    public Widget getWidget() {
	return layout.getWidget();
    }

    private void changeVisibility(Visibility newVisibility, Page<?> page) {
	if (pages.contains(page)) {
	    if (newVisibility == Visibility.focused) {
		focus(page);
	    } else if (newVisibility == Visibility.hidden) {
		hide(page);
	    } else if (newVisibility == Visibility.toggle && page.getVisibility() == Visibility.focused) {
		hide(page);
	    }
	}
    }

    private void focus(Page<?> page) {
	assert currentPagePresenter == null : "Only one page in overlay";
	this.currentPagePresenter = page;
	Widget widget = currentPagePresenter.getDisplay().asWidget();
	layout.add(widget);
    }

    private void hide(Page<?> page) {
	if (currentPagePresenter == page) {
	    final Widget widget = currentPagePresenter.getDisplay().asWidget();
	    layout.remove(widget);
	    currentPagePresenter = null;
	}
    }

}
