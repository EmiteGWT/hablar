package com.calclab.hablar.client.ui.menu;

public interface PopupMenuView<T> {
    void hide();

    boolean isVisible();

    void setTarget(T target);

    void show(int left, int top);
}
