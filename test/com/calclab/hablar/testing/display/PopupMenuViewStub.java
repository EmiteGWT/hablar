package com.calclab.hablar.testing.display;

import com.calclab.hablar.basic.client.ui.menu.MenuAction;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.google.gwt.user.client.ui.UIObject;

public class PopupMenuViewStub<T> implements PopupMenuView<T> {

    @Override
    public void addAction(MenuAction<T> action) {

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

    @Override
    public void showRelativeToMenu(UIObject ui) {
    }

}
