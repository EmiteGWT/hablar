package com.calclab.hablar.core.client.ui.icon;

/**
 * A class to retrieve, mostly, default icons
 *
 * <pre>
 * HablarStyles.get(IconType.buddy);
 * </pre>
 */
public class HablarIcons {

    private static HablarIconsBundle bundle;

    public static enum StyleType {
	active, inactive
    }

    static HablarIcons instance;

    public static void setStyles(final HablarIcons hablarIcons) {
	instance = hablarIcons;
    }

    public static void setBundle(HablarIconsBundle bundle) {
	HablarIcons.bundle = bundle;
    }

    public static HablarIconsBundle getBundle() {
	return bundle;
    }
}
