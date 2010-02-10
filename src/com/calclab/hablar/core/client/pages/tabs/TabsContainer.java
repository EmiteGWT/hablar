package com.calclab.hablar.core.client.pages.tabs;

import com.calclab.hablar.core.client.container.main.MainContainer;
import com.calclab.hablar.core.client.container.main.MainLayout;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;

public class TabsContainer extends MainContainer {

    public TabsContainer(HablarEventBus eventBus, MainLayout layout) {
	super(eventBus, layout);
    }

    @Override
    protected void toggle(Page<?> page) {
	if (page.getVisibility() == Visibility.notFocused) {
	    focus(page);
	} else {
	    super.toggle(page);
	}

    }

}