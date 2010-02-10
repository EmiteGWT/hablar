package com.calclab.hablar.core.client.container;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.user.client.ui.Widget;

public interface PagesContainer {

    boolean add(Page<?> page);

    String getRol();

    Widget getWidget();

}
