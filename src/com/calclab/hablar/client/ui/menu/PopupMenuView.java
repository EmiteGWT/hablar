package com.calclab.hablar.client.ui.menu;

public interface PopupMenuView<T> {
    void setTarget(T target);

    void show(int x, int y);
}
