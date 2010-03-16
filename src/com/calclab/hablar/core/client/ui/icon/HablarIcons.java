package com.calclab.hablar.core.client.ui.icon;

/**
 * A class to retrieve, mostly, default icons
 * 
 * <pre>
 * HablarStyles.get(IconType.buddy);
 * </pre>
 */
public class HablarIcons {
    public static enum IconType {
	buddy, buddyDnd, buddyOff, buddyOn, buddyWait, roster, menu, chat, chatAdd, search, on, off, close, buddyAdd, console, loading, groupChat, clipboard
    }

    public static enum StyleType {
	active, inactive
    }

    static HablarIcons instance;

    public static String get(final IconType iconType) {
	return instance.getIconStyle(iconType);
    }

    public static void setStyles(final HablarIcons hablarIcons) {
	instance = hablarIcons;
    }

    public static String styleOf(final StyleType type) {
	return instance.getStyleOf(type);
    }

    protected String getIconStyle(final HablarIcons.IconType iconType) {
	return iconType.toString();
    }

    protected String getStyleOf(final HablarIcons.StyleType type) {
	return type.toString();
    }

}
