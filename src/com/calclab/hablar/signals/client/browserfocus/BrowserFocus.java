package com.calclab.hablar.signals.client.browserfocus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

/**
 * Simple utility class which tracks whether the main application browser window currently
 * has focus or not.
 */
public class BrowserFocus {

    /**
     * Flag denoting whether the main application browser window currently has focus. We
     * will assume it has focus when the application first starts
     */
    private boolean browserHasFocus = true;

    /**
     * Flag denoting whether we have received any focus events since it started.
     */
    private boolean focusEventsReceived = false;

    /**
     * Private singleton instance
     */
    private static BrowserFocus instance = GWT.create(BrowserFocus.class);
    
    /**
     * Default constructor
     */
    protected BrowserFocus() {
	addFocusListenerEvents();
    }
    
    /**
     * Should be called as soon as possible so that we can start monitoring
     * focus events. Essentially calling this will just trigger the static
     * instance to be created.
     */
    public static void startMonitoring() {
	if(instance == null) {
	    instance = GWT.create(BrowserFocus.class);
	}
    }

    /**
     * Return whether we have received any focus events. If we haven't then the
     * output from {@link BrowserFocus#browserHasFocus()} cannot be relied upon.
     * 
     * @return true if browser focus events have been received.
     */
    public static boolean focusEventsReceived() {
	return instance.focusEventsReceived;
    }

    /**
     * Returns whether the browser window currently has focus. The output cannot
     * be relied upon (but will return <code>true</code>) if
     * {@link BrowserFocus#focusEventsReceived()} returns false. Essentially we assume
     * that if we haven't received any events then the window is still focussed (this may
     * not necessarily be the case).
     * 
     * @return <code>true</code> if the browser window currently has focus
     */
    public static boolean browserHasFocus() {
	return instance.browserHasFocus;
    }

    /**
     * Sets whether the browser window currently has focus
     * @param browserHasFocus
     */
    protected static void setBrowserHasFocus(final boolean browserHasFocus) {
	instance.browserHasFocus = browserHasFocus;
    }

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
        
        $wnd.onfocus = onBrowserFocus;
        $wnd.onblur = onBrowserBlur;
    }-*/;
}
