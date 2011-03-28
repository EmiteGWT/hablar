package com.calclab.hablar.core.client.pages.tabs;

import com.calclab.hablar.core.client.container.main.MainContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.google.gwt.core.client.GWT;

public class TabsContainer extends MainContainer {

    private final TabsMenuPresenter tabsMenuPresenter;

    public TabsContainer(final HablarEventBus eventBus, final TabsLayout layout) {
	super(eventBus, layout);
	tabsMenuPresenter = layout.getTabsMenuPresenter();
    }

    @Override
    protected void focus(final Page<?> page) {
	GWT.log("TABSCONTAINER FOCUS");
	super.focus(page);
	tabsMenuPresenter.add(page);
    }

    @Override
    protected boolean hide(final Page<?> page) {
	if (super.hide(page)) {
	    tabsMenuPresenter.remove(page);
	    return true;
	}
	return false;
    }

    @Override
    protected void toggle(final Page<?> page) {
	if (page.getVisibility() == Visibility.notFocused) {
	    focus(page);
	} else {
	    super.toggle(page);
	}

    }

}