package com.calclab.hablar.client.ui.lists;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.UIObject;


public interface ListLogic {

    void onItemClick(ListItemView view, Event event);

    void onMenuClicked(ListItemView view, UIObject menu);

    void onMouseOver(ListItemView view, boolean over);

}
