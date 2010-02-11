package com.calclab.hablar.user.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.page.Page;

public interface EditorPage<T extends Display> extends Page<T> {

    void saveData();

    void showData();

}
