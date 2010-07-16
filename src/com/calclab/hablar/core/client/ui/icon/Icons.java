package com.calclab.hablar.core.client.ui.icon;

import java.util.HashMap;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * A registry of icons
 */
public class Icons {
    /**
     * some widely used icon tokens. TODO: move some to its own modules (ie.
     * console, clipboard)
     **/
    /**
     * the default icon
     **/
    public static final String DEFAULT = "default";
    public static final String BUDDY_ADD = "add";
    public static final String BUDDY = "buddy";
    public static final String BUDDY_DND = "buddy_dnd";
    public static final String BUDDY_OFF = "buddy_off";
    public static final String BUDDY_ON = "buddy_on";
    public static final String BUDDY_WAIT = "buddy_wait";
    public static final String ADD_CHAT = "add_chat";
    public static final String CHAT = "chat";
    public static final String CLIPBOARD = "clipboard";
    public static final String CLOSE = "close";
    public static final String CONSOLE = "console";
    public static final String ADD_GROUP = "group_add";
    public static final String GROUP_CHAT = "group_chat";
    public static final String LOADING = "loading";
    public static final String MENU = "menu";
    public static final String NOT_CONNECTED = "off";
    public static final String CONNECTED = "on";
    public static final String ROSTER = "roster";
    public static final String SEARCH = "search";

    private static HashMap<String, ImageResource> icons = new HashMap<String, ImageResource>();

    /**
     * Get a icon with the given token. If no image is associated to that token,
     * then in returns the image associated to the token ImageIcons.DEFAULT
     * 
     * @param token
     *            the token of the icon to retrieve
     * @return the icon if any or a default icon
     */
    public static ImageResource get(final String token) {
	return icons.get(token);
    }

    /**
     * Register (and replace if any) the image of the given resource
     * 
     * @param token
     * @param image
     * @return
     */
    public static ImageResource register(final String token, final ImageResource image) {
	return icons.put(token, image);
    }

    /**
     * Utility method to change the icon of an image
     * 
     * @param image
     * @param token
     */
    public static void set(final Image image, final String token) {
	image.setUrl(get(token).getURL());
    }

    private Icons() {
    }
}
