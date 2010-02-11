package com.calclab.hablar.testing.display;

import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.PopupMenuView;

public class PopupMenuViewStub<T> implements PopupMenuView<T> {

    @Override
    public void addAction(Action<T> action) {

    }

    @Override
    public void hide() {
    }

    @Override
    public boolean isVisible() {
	return false;
    }

    @Override
    public void setTarget(T target) {
    }

    @Override
    public void show(int left, int top) {
    }

}
