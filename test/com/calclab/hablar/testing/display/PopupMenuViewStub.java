package com.calclab.hablar.testing.display;

import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Widget;

public class PopupMenuViewStub<T> implements MenuDisplay<T> {

    @Override
    public void addAction(final Action<T> action, final Command command) {
    }

    @Override
    public Widget asWidget() {
	return null;
    }

    @Override
    public void clearActions() {
    }

    @Override
    public void hide() {
    }

    @Override
    public boolean isVisible() {
	return false;
    }

    @Override
    public void removeAction(final Action<T> action) {
    }

    @Override
    public void setActionVisible(final Action<T> action, final boolean visible) {
    }

    @Override
    public void show(final int left, final int top) {
    }

}
