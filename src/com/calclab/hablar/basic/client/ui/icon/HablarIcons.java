package com.calclab.hablar.basic.client.ui.icon;

/**
 * A class to retrieve, mostly, default icons
 * 
 * <pre>
 * HablarStyles.get(IconType.buddy);
 * </pre>
 */
public class HablarIcons {
    public static enum IconType {
	buddy, buddyDnd, buddyOff, buddyOn, buddyWait, roster, menu, chat, chatAdd, search, on, off
    }

    public static enum StyleType {
	active, inactive
    }

    static HablarIcons instance;

    public static String get(IconType iconType) {
	return instance.getIconStyle(iconType);
    }

    public static void setStyles(HablarIcons hablarIcons) {
	instance = hablarIcons;
    }

    public static String styleOf(StyleType type) {
	return instance.getStyleOf(type);
    }

    protected String getIconStyle(HablarIcons.IconType iconType) {
	return iconType.toString();
    }

    protected String getStyleOf(HablarIcons.StyleType type) {
	return type.toString();
    }

}
