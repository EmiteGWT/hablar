package com.calclab.hablar.core.client.pages.tabs;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.UIObject;

public class ExtendedScrollPanel extends ScrollPanel {

    @Override
    public void ensureVisible(final UIObject item) {
	final Element scroll = getElement();
	final Element element = item.getElement();
	ensureVisibleImpl(scroll, element);
    }

    /**
     * 
     * This ensureVisible works also vertically (needed for the tabsBar)
     * 
     */
    private native void ensureVisibleImpl(Element scroll, Element e) /*-{
        if (!e)
        return;

        var item = e;
        var realOffset = 0;
        var realOffsetH = 0;
        while (item && (item != scroll)) {
        realOffset += item.offsetTop;
        realOffsetH += item.offsetLeft;
        item = item.offsetParent;
        }

        scroll.scrollTop = realOffset - scroll.offsetHeight / 2;
        scroll.scrollLeft = (realOffsetH - scroll.offsetWidth / 2) + 5;
    }-*/;
}
