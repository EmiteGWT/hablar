package com.calclab.hablar.user.client;

import java.util.ArrayList;

import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedHandler;
import com.google.gwt.user.client.ui.Widget;

public class UserContainer implements PagesContainer {
    public static final String ROL = "User";
    private final ArrayList<UserPage<?>> pages;
    private final UserPresenter userPage;

    public UserContainer(HablarEventBus eventBus, final UserPresenter userPage) {
	this.userPage = userPage;
	this.pages = new ArrayList<UserPage<?>>();

	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		if (event.getPage() == userPage) {
		    userPageVisibilityChanged(event.getVisibility());
		}
	    }
	});
    }

    @Override
    public boolean add(Page<?> page) {
	UserPage<?> toAdd = (UserPage<?>) page;
	userPage.addPage(toAdd);
	pages.add(toAdd);
	page.setVisibility(Visibility.hidden);
	return true;
    }

    public boolean focus(Page<?> page) {
	return pages.contains(page);
    }

    @Override
    public String getRol() {
	return ROL;
    }

    @Override
    public Widget getWidget() {
	return userPage.getDisplay().asWidget();
    }

    public boolean hide(Page<?> page) {
	return false;
    }

    public boolean unfocus(Page<?> page) {
	return pages.contains(page);
    }

    protected void userPageVisibilityChanged(Visibility visibility) {
	if (visibility == Visibility.notFocused) {
	    for (UserPage<?> page : pages) {
		page.setVisibility(Visibility.hidden);
		page.afterClosed();
	    }
	    userPage.afterClosed();
	} else if (visibility == Visibility.focused) {
	    for (UserPage<?> page : pages) {
		page.setVisibility(Visibility.focused);
		page.beforeOpen();
	    }
	    userPage.beforeOpen();
	}
    }

}
