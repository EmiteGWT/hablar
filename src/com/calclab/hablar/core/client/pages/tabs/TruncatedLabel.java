package com.calclab.hablar.core.client.pages.tabs;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Label;

public class TruncatedLabel extends Label {

    private int trim;

    public int getTrim() {
	return trim;
    }

    @Override
    public void setText(final String text) {
	super.setText(text);
	String userAgent = getUserAgent();
	if (userAgent.contains("gecko")) { // Needed only for Firefox
	    Element element = getElement();
	    Element parent = element.getParentElement();
	    if (parent != null) {
		element.removeFromParent();
		parent.appendChild(element);
	    }
	}
    }

    public static native String getUserAgent() /*-{
        return navigator.userAgent.toLowerCase();
    }-*/;
}
