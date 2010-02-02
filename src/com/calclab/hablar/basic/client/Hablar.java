package com.calclab.hablar.basic.client;

import com.calclab.hablar.basic.client.ui.Display;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.pages.Pages;

public interface Hablar {

    void closeOverlay();

    HablarEventBus getHablarEventBus();

    Pages getPages();

    void setDocked(PageView pageView, int size);

    void showOverlay(Display display);

}
