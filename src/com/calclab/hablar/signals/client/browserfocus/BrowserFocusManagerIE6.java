package com.calclab.hablar.signals.client.browserfocus;

public class BrowserFocusManagerIE6 {
    /**
     * Adds the focus events to the browser
     */
    protected native void addFocusListenerEvents() /*-{
        $wnd.document.onfocusin = function() {
            this.@com.calclab.hablar.signals.client.browserfocus.BrowserFocusManager::setHasFocus(Z)(true);
        };
        $wnd.document.onfocusout = function() {
            this.@com.calclab.hablar.signals.client.browserfocus.BrowserFocusManager::setHasFocus(Z)(false);
        };
    }-*/;
}
