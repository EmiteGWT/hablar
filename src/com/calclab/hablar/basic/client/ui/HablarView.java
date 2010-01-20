package com.calclab.hablar.basic.client.ui;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.pages.Pages;

public interface HablarView {

    Pages getPages();

    void setDocked(PageView pageView, int size);

}
