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
    private final OverlayPanel panel;

    public OverlayContainer(final HablarEventBus eventBus, final OverlayPanel panel) {
	this.panel = panel;
	pages = new ArrayList<Page<?>>();
	panel.setVisible(false);
	VisibilityChangeRequestEvent.bind(eventBus, new VisibilityChangeRequestHandler() {
	    @Override
	    public void onVisibilityChangeRequest(final VisibilityChangeRequestEvent event) {
		changeVisibility(event.getNewVisibility(), event.getPage());
	    }
	});
    }

    @Override
    public boolean add(final Page<?> page) {
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
	return panel.getWidget();
    }

    private void changeVisibility(final Visibility newVisibility, final Page<?> page) {
	if (pages.contains(page)) {
	    if (newVisibility == Visibility.focused) {
		focus(page);
	    } else if ((newVisibility == Visibility.hidden) || (newVisibility == Visibility.notFocused)) {
		hide(page);
	    } else if ((newVisibility == Visibility.toggle) && (page.getVisibility() == Visibility.focused)) {
		hide(page);
	    }
	}
    }

    private void focus(final Page<?> page) {
	assert currentPagePresenter == null : "Only one page in overlay";
	currentPagePresenter = page;
	final Widget widget = currentPagePresenter.getDisplay().asWidget();
	page.setVisibility(Visibility.focused);
	panel.add(widget);
    }

    private void hide(final Page<?> page) {
	if (currentPagePresenter == page) {
	    final Widget widget = currentPagePresenter.getDisplay().asWidget();
	    panel.remove(widget);
	    currentPagePresenter = null;
	    page.setVisibility(Visibility.hidden);
	}
    }

}
