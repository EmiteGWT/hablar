package com.calclab.hablar.basic.client.ui;

import com.google.gwt.user.client.Element;

public class UIUtils {

    public native static void setTextSelectionEnabled(Element element, boolean enabled) /*-{
        if (!enabled) { 
        e.ondrag = function () { return false; }; 
        e.onselectstart = function () { return false; }; 
        } else { 
        e.ondrag = null; 
        e.onselectstart = null; 
        }
    }-*/;

}
