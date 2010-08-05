package com.calclab.hablar.core.client.browser;

public class BrowserFocusHandlerIE6 extends BrowserFocusHandler {
    /**
     * Adds the focus events to the browser
     * 
     * Thanks to
     * http://www.sitepen.com/blog/2008/10/13/the-cross-browser-window-
     * focus-blues/
     */
    @Override
    protected native void addFocusListenerEvents() /*-{
        $wnd.document.onfocusin = function() {
        @com.calclab.hablar.core.client.browser.BrowserFocusHandler::changeFocus(Z)(true);
        };
        $wnd.document.onfocusout = function() {
        if($wnd._BrowserFocusHandlerIE6ActiveElement != $wnd.document.activeElement) {
        $wnd._BrowserFocusHandlerIE6ActiveElement = $wnd.document.activeElement;
        } else {
        @com.calclab.hablar.core.client.browser.BrowserFocusHandler::changeFocus(Z)(false);
        }
        };
        $wnd._BrowserFocusHandlerIE6ActiveElement = $wnd.document.activeElement;
    }-*/;
}
