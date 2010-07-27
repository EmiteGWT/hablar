package com.calclab.hablar.core.client.browser;

public class BrowserFocusHandlerIE6 extends BrowserFocusHandler {
    /**
     * Adds the focus events to the browser
     */
    @Override
    protected native void addFocusListenerEvents() /*-{
        $wnd.document.onfocusin = function() {
        @com.calclab.hablar.core.client.browser.BrowserFocusHandler::changeFocus(Z)(true);
        };
        $wnd.document.onfocusout = function() {
        @com.calclab.hablar.core.client.browser.BrowserFocusHandler::changeFocus(Z)(false);
        };
    }-*/;
}
