package com.calclab.hablar.core.client.browser;

import com.google.gwt.core.client.GWT;

/**
 * Simple utility class which tracks whether the main application browser window
 * currently has focus or not.
 */
public class BrowserFocusHandler {

    public static interface BrowserFocusListener {
	public void onBrowserFocusChanged(boolean newFocus);
    }

    private static BrowserFocusHandler instance;
    private boolean hasFocus;
    private BrowserFocusListener focusListener;

    public BrowserFocusHandler() {
	GWT.log("BrowserFocusHandler - init");
	this.focusListener = new BrowserFocusListener() {
	    @Override
	    public void onBrowserFocusChanged(boolean newFocus) {
	    }
	};
	this.hasFocus = true;
	addFocusListenerEvents();
    }

    /**
     * Set the a listener to know when the browser get or looses the focus.
     * 
     * @param focusListener
     *            can't be null
     */
    public void setFocusListener(BrowserFocusListener focusListener) {
	assert focusListener != null;
	this.focusListener = focusListener;
    }

    /**
     * Expose the method to javascript (do not use this function, use
     * getInstance().setFocus() instead)
     * 
     * @param hasFocus
     */
    public static void changeFocus(final boolean hasFocus) {
	getInstance().setFocus(hasFocus);
    }

    /**
     * Adds the focus events to the browser
     */
    protected native void addFocusListenerEvents() /*-{
        $wnd.onfocus = function() {
        @com.calclab.hablar.core.client.browser.BrowserFocusHandler::changeFocus(Z)(true);
        };
        $wnd.onblur = function() {
        @com.calclab.hablar.core.client.browser.BrowserFocusHandler::changeFocus(Z)(false);
        };
    }-*/;

    /**
     * Sets current browser focus status
     * 
     * @param hasFocus
     */
    public void setFocus(boolean hasFocus) {
	GWT.log("BrowserFocusHandler - focus: " + hasFocus);
	this.hasFocus = hasFocus;
	focusListener.onBrowserFocusChanged(hasFocus);
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

    public static BrowserFocusHandler getInstance() {
	if (instance == null) {
	    instance = GWT.create(BrowserFocusHandler.class);
	}
	return instance;
    }

    public static void setInstance(BrowserFocusHandler newInstance) {
	instance = newInstance;
    }

}
