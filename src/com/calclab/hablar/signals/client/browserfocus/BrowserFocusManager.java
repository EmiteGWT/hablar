package com.calclab.hablar.signals.client.browserfocus;

import com.google.gwt.core.client.GWT;

/**
 * Simple utility class which tracks whether the main application browser window
 * currently has focus or not.
 */
public class BrowserFocusManager {

    private static BrowserFocusManager instance;
    private boolean hasFocus;
    
    public BrowserFocusManager() {
	this.hasFocus = true;
    }
    
    /**
     * Adds the focus events to the browser
     */
    protected native void addFocusListenerEvents() /*-{
        var onBrowserFocus = function() {
        this@com.calclab.hablar.signals.client.browserfocus.BrowserFocusManager::setBrowserHasFocus(Z)(true);
        }

        var onBrowserBlur = function() {
        this@com.calclab.hablar.signals.client.browserfocus.BrowserFocusManager::setBrowserHasFocus(Z)(false);
        }

        $wnd.onfocus = onBrowserFocus;
        $wnd.onblur = onBrowserBlur;
    }-*/;
    
    /**
     * Sets whether the browser window currently has focus or not (usually called by jsni)
     * 
     * @param hasFocus
     */
    public void setHasFocus(boolean hasFocus) {
	GWT.log("Browser focus: " + hasFocus);
	this.hasFocus = hasFocus;
    }
    
    /**
     * Returns whether the browser window currently has focus. The output cannot
     * be relied upon (but will return <code>true</code>) if
     * {@link BrowserFocus#focusEventsReceived()} returns false. Essentially we
     * assume that if we haven't received any events then the window is still
     * focussed (this may not necessarily be the case).
     * 
     * @return <code>true</code> if the browser window currently has focus
     */
    public boolean hasFocus() {
	return hasFocus;
    }
    
    public static BrowserFocusManager getInstance() {
	if (instance == null) {
	    instance = GWT.create(BrowserFocusManager.class);
	}
	return instance;
    }
    
    public static void setInstance(BrowserFocusManager newInstance) {
	instance = newInstance;
    }
    
}
