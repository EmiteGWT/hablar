package com.calclab.hablar.core.client.mvp;

public interface Presenter<T extends Display> {
    T getDisplay();
}
