package com.calclab.hablar.core.client.ui.selectionlist;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface Selectable extends Comparable<Selectable> {

    String getId();

    Widget getWidget();

    HasClickHandlers getAction();

    Object getItem();
}
