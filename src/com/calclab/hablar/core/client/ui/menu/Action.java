package com.calclab.hablar.core.client.ui.menu;


public interface Action<T> {

    void execute(T target);

    String getIcon();

    String getId();

    String getName();

    boolean isApplicable(T target);

}
