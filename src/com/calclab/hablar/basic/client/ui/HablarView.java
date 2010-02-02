package com.calclab.hablar.basic.client.ui;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.pages.Pages;

public interface HablarView {

    void closeOverlay();

    Pages getPages();

    void setDocked(PageView pageView, int size);

    void showOverlay(Display display);

}
