package com.calclab.hablar.signals.client.notifications;

import static com.calclab.hablar.signals.client.I18nSignals.i18n;

import com.calclab.hablar.core.client.browser.BrowserFocusHandler;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Timer;

/**
 * A {@link HablarNotifier} which uses browser popups to notify the user of new
 * messages.
 */
public class BrowserPopupHablarNotifier implements HablarNotifier {

    /**
     * The time (in milliseconds) to display each message (10s)
     */
    private static final int POPUP_MESSAGE_TIMER_MILLIS = 10000;

    /**
     * The width (in pixels) for the popup
     */
    private static final int POPUP_WIDTH = 300;

    /**
     * The height (in pixels) that each message should be Note - this needs to
     * match the setting in the Hablar.css file
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
    private static final int CREATE_TOASTER_WINDOW_ALREDY_OPEN = 1;

    /**
     * Value returned from
     * {@link BrowserPopupHablarNotifier#createToasterWindow(int, int, String)}
     * if a new window is successfully opened
     */
    private static final int CREATE_TOASTER_WINDOW_NEWLY_OPENED = 2;

    /**
     * This is used to populate the popup when it's created. It can also be
     * overridden by calling
     * {@link BrowserPopupHablarNotifier#setPopupHtml(String)}.
     * 
     * I have inlined the css here so that it is loaded before the content,
     * otherwise if I use an external stylesheet then there is a moment where
     * the content is unstyled and it looks a bit rubbish.
     */
    private static String popupHtml = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"
	    + "<html><head>"
	    + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">"
	    + "<style type=\"text/css\">"
	    + "html { font-family: Tahoma, sans-serif; font-size: 80%; } "
	    + "body { background-color: #e9e9e9; margin: 0; padding: 0;	} "
	    + "#notificationContainer { width: 100%; height: 100%; } "
	    + "#notificationContainer p.message { height: 30px; line-height: 30px; margin: 10px 10px 0 10px !important; padding: 0 2px;"
	    + " overflow: hidden; white-space: nowrap; text-align: center; background-color: black; -moz-border-radius: 5px;"
	    + " -webkit-border-radius: 5px; border-radius: 5px; } "
	    + "#notificationContainer p.message a, #notificationContainer p.message a:visited { color: white; text-decoration: none; }"
	    + "</style>"
	    + "</head><body class=\"hablarPopupWindow\"><div id=\"notificationContainer\"></div></body></html>";

    private static native int getScreenHeight() /*-{
						return $wnd.screen.height;
						}-*/;

    /**
     * Set the html which will be used to populate the popup window. I know I
     * shouldn't really do it like this, but at least it's simple!
     */
    public static void setPopupHtml(final String popupHtml) {
	BrowserPopupHablarNotifier.popupHtml = popupHtml;
    }

    /**
     * A counter of the number of messages in the current popup window
     */
    private int messageCount;

    /**
     * Construct a new {@link BrowserPopupHablarNotifier}.
     */
    public BrowserPopupHablarNotifier() {
	messageCount = 0;
    }

    /**
     * Adds a message to the popup
     * 
     * @param userMessage
     *            the message
     */
    private void addMessage(final String userMessage) {
	final JavaScriptObject newMessage = addMessageNative(userMessage);

	if (newMessage != null) {
	    messageCount++;

	    resizePopupWindow();

	    final BrowserPopupHablarNotifier notifier = this;

	    final Timer timer = new Timer() {
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

									       var document = $wnd.toasterWindow.document;

									       var messageDiv = document.createElement("p");
									       messageDiv.className = "message";

									       var anchorDiv = document.createElement("a");
									       anchorDiv.setAttribute("href", "#");
									       anchorDiv.setAttribute("title", userMessage);

									       anchorDiv.onclick = function() {
									       // If the user clicks a message, focus the main window and close the popup
									       if ($wnd.toasterWindow && $wnd.toasterWindow.opener) {
									       if ($wnd.toasterWindow.opener.focus) {
									       $wnd.toasterWindow.opener.focus();
									       }
									       $wnd.toasterWindow.close();
									       }

									       return false;
									       }

									       anchorDiv.appendChild(document.createTextNode(userMessage));

									       messageDiv.appendChild(anchorDiv);

									       document.getElementById("notificationContainer").appendChild(messageDiv);

									       return messageDiv;
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
												  try {
												  // If the toaster window is already open, our work is done!
												  if($wnd.toasterWindow && !$wnd.toasterWindow.closed) {
												  return @com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::CREATE_TOASTER_WINDOW_ALREDY_OPEN;
												  }

												  var screenWidth = $wnd.screen.width;
												  var screenHeight = $wnd.screen.height;

												  var left = screenWidth - width;
												  var top = screenHeight - height;

												  // We will include a random element to the window handle
												  // just in case we have multiple chat clients running within
												  // one browser instance
												  var windowHandle = "chatNotifierPopup" + Math.floor(Math.random() * 1000000);

												  $wnd.toasterWindow = $wnd.open("", windowHandle, "status=no" +
												  ",toolbar=no,location=yes,menubar=no,directories=no" +
												  ",resizable=yes,width=" + width + ",height=" + height +
												  ",left=" + left + ",top=" + top + ",alwaysRaised=yes," +
												  ",scrollbars=yes");

												  if($wnd.toasterWindow == null) {
												  // Popups must be blocked :(
												  return @com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::CREATE_TOASTER_WINDOW_FAILURE;
												  }

												  $wnd.toasterWindow.document.write(@com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::popupHtml);

												  $wnd.toasterWindow.document.title = title;
												  $wnd.toasterWindow.focus();

												  var notifier = this;

												  return @com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::CREATE_TOASTER_WINDOW_NEWLY_OPENED;
												  } catch (e) {
												  return @com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier::CREATE_TOASTER_WINDOW_FAILURE;
												  }
												  }-*/;

    @Override
    public String getDisplayName() {
	return i18n().browserPopupNotifierDisplayName();
    }

    @Override
    public String getId() {
	return "browserPopupNotifier";
    }

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
						  var messageContainer = $wnd.toasterWindow.document.getElementById("notificationContainer");

						  while(messageContainer.hasChildNodes()) {
						  messageContainer.removeChild(messageContainer.lastChild);
						  }

						  $wnd.toasterWindow.close();
						  }        
						  $wnd.toasterWindow = null;
						  }-*/;

    /**
     * Remove a message from the popup
     * 
     * @param messageDiv
     *            the element to remove
     */
    private void removeMessage(final JavaScriptObject messageDiv) {
	final boolean found = removeMessageNative(messageDiv);

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

    @Override
    public void show(final String userMessage, final String messageType) {
	// Show the message only if the browser window doesn't have focus.
	if (!BrowserFocusHandler.getInstance().hasFocus()) {
	    final int success = createToasterWindow(POPUP_WIDTH, POPUP_MESSAGE_HEIGHT + POPUP_STATIC_HEIGHT, i18n()
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
}
