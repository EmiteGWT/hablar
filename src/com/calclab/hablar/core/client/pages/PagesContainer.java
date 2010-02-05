package com.calclab.hablar.core.client.pages;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.user.client.ui.Widget;

public interface PagesContainer {

    boolean add(Page<?> page);

    boolean focus(Page<?> page);

    String getRol();

    Widget getWidget();

    boolean hide(Page<?> page);

    boolean remove(Page<?> page);

    boolean unfocus(Page<?> page);
}
