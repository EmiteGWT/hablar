package com.calclab.hablar.core.client.ui.menu;


public interface PopupMenuView<T> {
    void addAction(Action<T> action);

    void hide();

    boolean isVisible();

    void setTarget(T target);

    void show(int left, int top);

}
