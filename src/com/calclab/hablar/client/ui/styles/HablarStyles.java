package com.calclab.hablar.client.ui.styles;

public class HablarStyles {
    public static enum IconType {
	buddy, buddyDnd, buddyOff, buddyOn, buddyWait, roster, menu, chat, chatAdd, search
    }

    public static enum StyleType {
	active, inactive
    }

    static HablarStyles instance;

    public static String get(IconType iconType) {
	return instance.getIconStyle(iconType);
    }

    public static void setStyles(HablarStyles hablarStyles) {
	instance = hablarStyles;
    }

    public static String styleOf(StyleType type) {
	return instance.getStyleOf(type);
    }

    protected String getIconStyle(HablarStyles.IconType iconType) {
	return iconType.toString();
    }

    protected String getStyleOf(HablarStyles.StyleType type) {
	return type.toString();
    }

}
