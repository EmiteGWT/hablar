package com.calclab.hablar.core.client.ui.icon;

import java.util.HashMap;

import com.google.gwt.resources.client.ImageResource;

/**
 * A registry of icons
 */
public class Icons {
    private static HashMap<String, ImageResource> icons = new HashMap<String, ImageResource>();

    /**
     * Get a icon with the given token. If no image is associated to that token,
     * then in returns the image associated to the token ImageIcons.DEFAULT
     * 
     * @param token
     *            the token of the icon to retrieve
     * @return the icon if any or a default icon
     */
    public static ImageResource get(String token) {
	return icons.get(token);
    }

    public static ImageResource register(String token, ImageResource image) {
	return icons.put(token, image);
    }

    private Icons() {
    }
}
