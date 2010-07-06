package com.calclab.hablar.signals.client.browserfocus;

/**
 * Simple utility class which tracks whether the main browser window currently
 * has focus or not.
 */
public class BrowserFocusIE6 extends BrowserFocus {
    /**
     * Adds the focus events to the browser
     */
    protected native void addFocusListenerEvents() /*-{
        var onBrowserFocus = function() {
            @com.calclab.hablar.signals.client.browserfocus.BrowserFocus::setBrowserHasFocus(Z)(true);
        }

        var onBrowserBlur = function() {
            @com.calclab.hablar.signals.client.browserfocus.BrowserFocus::setBrowserHasFocus(Z)(false);
        }

        $wnd.document.onfocusin = onBrowserFocus;
        $wnd.document.onfocusout = onBrowserBlur;
    }-*/;
}
