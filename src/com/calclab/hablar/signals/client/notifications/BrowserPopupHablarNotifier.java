package com.calclab.hablar.signals.client.notifications;

import static com.calclab.hablar.signals.client.I18nSignals.i18n;

import com.calclab.hablar.signals.client.browserfocus.BrowserFocus;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Timer;

/**
 * A {@link HablarNotifier} which uses browser popups to notify the user of new
 * messages.
 */
public class BrowserPopupHablarNotifier implements HablarNotifier {

    /**
     * The width (in pixels) for the popup
     */
    private static final int POPUP_WIDTH = 300;

    /**
     * The height (in pixels) that each message should be
     */
    private static final int POPUP_MESSAGE_HEIGHT = 40;

    /**
     * The height (in pixels) extra to allow for the title, etc.
     */
    private static final int POPUP_STATIC_HEIGHT = 110;

    /**
     * The maximum height (in pixels) that the popup window should get to
     */
    private static final int POPUP_MAX_HEIGHT = getScreenHeight();

    /**
     * Value returned from
     * {@link BrowserPopupHablarNotifier#createToasterWindow(int, int, String)}
     * if the opening fails
     */
    private static final int CREATE_TOASTER_WINDOW_FAILURE = 0;

    /**
     * Value returned from
     * {@link BrowserPopupHablarNotifier#createToasterWindow(int, int, String)}
     * if the window is already open
     */
    @SuppressWarnings("unused")
    private static final int CREATE_TOASTER_WINDOW_ALREDY_OPEN = 1;

    /**
     * Value returned from
     * {@link BrowserPopupHablarNotifier#createToasterWindow(int, int, String)}
     * if a new window is successfully opened
     */
    private static final int CREATE_TOASTER_WINDOW_NEWLY_OPENED = 2;

    /**
     * The time (in milliseconds) to display each message (10s)
     */
    private static final int POPUP_MESSAGE_TIMER_MILLIS = 10000;
    
    @SuppressWarnings("unused")
    private static final String POPUP_HTML = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"
	    + "<html><head>"
	    + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">"
	    + "<link type=\"text/css\" rel=\"stylesheet\" href=\""
	    + GWT.getHostPageBaseURL()
	    + "Hablar.css\">"
	    + "</head><body><div id=\"notificationContainer\"></div></body></html>";

    private int messageCount;

    /**
     * Construct a new {@link BrowserPopupHablarNotifier}.
     */
    public BrowserPopupHablarNotifier() {
	messageCount = 0;
    }

    @Override
    public void show(final String userMessage, final String messageType) {
	// Show the message only if the browser window doesn't have focus.
	if (!BrowserFocus.browserHasFocus()) {
	    int success = createToasterWindow(POPUP_WIDTH, POPUP_MESSAGE_HEIGHT + POPUP_STATIC_HEIGHT, i18n()
		    .browserPopupNotifierWindowTitle());

	    if (success != CREATE_TOASTER_WINDOW_FAILURE) {
		if (success == CREATE_TOASTER_WINDOW_NEWLY_OPENED) {
		    // If we have just opened a new window, reset the message
		    // count
		    messageCount = 0;
		}
		addMessage(userMessage);
	    }
	}
    }

    /**
     * Adds a message to the popup
     * 
     * @param userMessage
     *            the message
     */
    private void addMessage(final String userMessage) {
	final JavaScriptObject newMessage = addMessageNative(userMessage);

	if(newMessage != null) {
        	messageCount++;
        
        	resizePopupWindow();
        
        	final BrowserPopupHablarNotifier notifier = this;
        
        	Timer timer = new Timer() {
        	    @Override
        	    public void run() {
        		notifier.removeMessage(newMessage);
        	    }
        
        	};
        
        	timer.schedule(POPUP_MESSAGE_TIMER_MILLIS);
	}
    }

    /**
     * Called by addMessage
     * 
     * @param userMessage
     *            the message
     * @return the <code>div</code> element
     */
    private native JavaScriptObject addMessageNative(final String userMessage) /*-{
        if(!$wnd.toasterWindow) {
            return null;
        }

	var notifier = this;

        var document = $wnd.toasterWindow.document;
        
        var messageDiv = document.createElement("p");
        messageDiv.className = "message";
        
        var anchorDiv = document.createElement("a");
        anchorDiv.setAttribute("href", "#");
        anchorDiv.onclick = function() {
            if($wnd.toasterWindow.opener) {
            	$wnd.toasterWindow.opener.focus();
            }
            notifier.@com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::hideToasterWindow();
        }
        
        anchorDiv.appendChild(document.createTextNode(userMessage));

        messageDiv.appendChild(anchorDiv);
        
        document.getElementById("notificationContainer").appendChild(messageDiv);

        return messageDiv;
    }-*/;

    /**
     * Remove a message from the popup
     * 
     * @param messageDiv
     *            the element to remove
     */
    private void removeMessage(final JavaScriptObject messageDiv) {
	boolean found = removeMessageNative(messageDiv);

	if (found) {
	    messageCount--;

	    if (messageCount < 1) {
		hideToasterWindow();
	    } else {
		resizePopupWindow();
	    }
	}
    }

    /**
     * Called by removeMessage
     * 
     * @param messageDiv
     *            the element to remove
     * @return <code>true</code> if the element was found and removed,
     *         <code>false</code> otherwise
     */
    private native boolean removeMessageNative(final JavaScriptObject messageDiv) /*-{
        var container = $wnd.toasterWindow.document.getElementById("notificationContainer");
        var element;

        for (var i = 0; i < container.childNodes.length; ++i) {
            element = container.childNodes[i];
            if(element == messageDiv) {
                container.removeChild(element);
                return true;
            }
        }

        return false;
    }-*/;

    /**
     * Resizes and repositions the popup window
     */
    private void resizePopupWindow() {
	int newSize = messageCount * POPUP_MESSAGE_HEIGHT + POPUP_STATIC_HEIGHT;

	if (newSize > POPUP_MAX_HEIGHT) {
	    newSize = POPUP_MAX_HEIGHT;
	}

	resizePopupWindowTo(POPUP_WIDTH, newSize);
    }

    /**
     * Called by resizePopupWindow.
     * 
     * @param width
     *            the width in px
     * @param height
     *            the height in px
     */
    private native void resizePopupWindowTo(final int width, final int height) /*-{
        // We can't resize something that doesn't exist
        if($wnd.toasterWindow && !$wnd.toasterWindow.closed) {
            var screenWidth = $wnd.screen.width;
            var screenHeight = $wnd.screen.height;

            var left = screenWidth - width;
            var top = screenHeight - height;

            $wnd.toasterWindow.moveTo(left, top);
            $wnd.toasterWindow.resizeTo(width, height);
            $wnd.toasterWindow.moveTo(left, top);
            $wnd.toasterWindow.resizeTo(width, height);
        }
    }-*/;

    /**
     * Removes all messages from the popup window and closes it.
     */
    private void hideToasterWindow() {
	hideToasterWindowNative();
	messageCount = 0;
    }

    /**
     * Called by hideToasterWindow
     */
    private native void hideToasterWindowNative() /*-{
        if($wnd.toasterWindow && !$wnd.toasterWindow.closed) {
            $wnd.toasterWindow.close();

            var messageContainer = $wnd.toasterWindow.document.getElementById("notificationContainer");

            while(messageContainer.hasChildNodes()) {
                messageContainer.removeChild(messageContainer.lastChild);
            }
        }        
        $wnd.toasterWindow = null;
    }-*/;

    /**
     * Creates the popup window if it's not already open
     * 
     * @param width
     * @param height
     * @param htmlPage
     * @param title
     * @return
     */
    private native int createToasterWindow(final int width, final int height, final String title) /*-{
        // If the toaster window is already open, our work is done!
        if($wnd.toasterWindow && !$wnd.toasterWindow.closed) {
            return @com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::CREATE_TOASTER_WINDOW_ALREDY_OPEN;
        }

        var screenWidth = $wnd.screen.width;
        var screenHeight = $wnd.screen.height;

        var left = screenWidth - width;
        var top = screenHeight - height;

        $wnd.toasterWindow = $wnd.open("", null, "status=no" +
        ",toolbar=no,location=yes,menubar=no,directories=no" +
        ",resizable=yes,width=" + width + ",height=" + height +
        ",left=" + left + ",top=" + top + ",alwaysRaised=yes," +
        ",scrollbars=yes");

        if($wnd.toasterWindow == null) {
            // Popups must be blocked :(
            return @com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::CREATE_TOASTER_WINDOW_FAILURE;
        }

        // I know this is a little non-ideal
        $wnd.toasterWindow.document.write(@com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::POPUP_HTML);

        $wnd.toasterWindow.document.title = title;
        $wnd.toasterWindow.focus();

        var notifier = this;

        return @com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::CREATE_TOASTER_WINDOW_NEWLY_OPENED;
    }-*/;

    @Override
    public String getId() {
	return "browserPopupNotifier";
    }

    @Override
    public String getDisplayName() {
	return i18n().browserPopupNotifierDisplayName();
    }

    private static native int getScreenHeight() /*-{
        return $wnd.screen.height;
    }-*/;
}
