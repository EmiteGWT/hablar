package com.calclab.hablar.user.client;

import com.calclab.hablar.core.client.mvp.Display;

public interface UserDisplay extends Display {

    void addPage(EditorPage<?> page);
}
