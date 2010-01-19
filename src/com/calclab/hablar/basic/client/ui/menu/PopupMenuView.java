package com.calclab.hablar.basic.client.ui.menu;

import com.google.gwt.user.client.ui.UIObject;

public interface PopupMenuView<T> {
    void hide();

    boolean isVisible();

    void setTarget(T target);

    void show(int left, int top);

    void showRelativeToMenu(UIObject ui);
}
