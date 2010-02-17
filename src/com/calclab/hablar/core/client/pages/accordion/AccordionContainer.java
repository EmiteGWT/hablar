package com.calclab.hablar.core.client.pages.accordion;

import com.calclab.hablar.core.client.container.main.MainContainer;
import com.calclab.hablar.core.client.container.main.MainLayout;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;

public class AccordionContainer extends MainContainer {

    private Page<?> lastFocused;

    public AccordionContainer(final HablarEventBus eventBus, final MainLayout layout) {
	super(eventBus, layout);
    }

    @Override
    protected void focus(final Page<?> page) {
	lastFocused = focusedPage;
	super.focus(page);
    }

    @Override
    protected boolean hide(final Page<?> page) {
	if (focusedPage == page && lastFocused != null) {
	    focus(lastFocused);
	    lastFocused = focusedPage;
	}
	return super.hide(page);
    }

    @Override
    protected void toggle(final Page<?> page) {
	if (page.getVisibility() == Visibility.focused) {
	    if (lastFocused != null) {
		focus(lastFocused);
	    }
	} else if (page.getVisibility() == Visibility.notFocused) {
	    focus(page);
	}
    }

}