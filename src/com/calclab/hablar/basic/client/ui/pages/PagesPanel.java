package com.calclab.hablar.basic.client.ui.pages;

import com.calclab.hablar.basic.client.ui.page.Page;

/**
 * Lo que se supone que tiene que implementar un planel
 */
public interface PagesPanel {
    void addPage(Page page);

    boolean hasPage(Page page);

    void removePage(Page page);

    void showPage(Page page);

}
