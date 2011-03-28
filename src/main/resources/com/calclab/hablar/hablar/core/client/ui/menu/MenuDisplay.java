package com.calclab.hablar.core.client.ui.menu;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.user.client.Command;

public interface MenuDisplay<T> extends Display {

    void addAction(Action<T> action, Command command);

    void clearActions();

    void hide();

    boolean isVisible();

    void removeAction(Action<T> action);

    void setActionVisible(Action<T> action, boolean visible);

    void show(int left, int top);
}
