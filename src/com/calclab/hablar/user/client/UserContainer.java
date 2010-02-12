package com.calclab.hablar.user.client;

import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.google.gwt.user.client.ui.Widget;

public class UserContainer implements PagesContainer {
    public static final String ROL = "User";
    private final UserPage userPage;

    public UserContainer(final HablarEventBus eventBus, final UserPage userPage) {
	this.userPage = userPage;
    }

    @Override
    public boolean add(final Page<?> page) {
	final EditorPage<?> toAdd = (EditorPage<?>) page;
	userPage.addPage(toAdd);
	page.setVisibility(Visibility.hidden);
	return true;
    }

    public boolean focus(final Page<?> page) {
	return userPage.contains(page);
    }

    @Override
    public String getRol() {
	return ROL;
    }

    @Override
    public Widget getWidget() {
	return userPage.getDisplay().asWidget();
    }

    public boolean hide(final Page<?> page) {
	return false;
    }

    public boolean unfocus(final Page<?> page) {
	return userPage.contains(page);
    }

}
