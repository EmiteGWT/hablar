package com.calclab.hablar.core.client.ui.icon;

/**
 * A class to retrieve, mostly, default icons
 * 
 * <pre>
 * HablarStyles.get(IconType.buddy);
 * </pre>
 */
public class OldHablarIcons {

    public static enum StyleType {
	active, inactive
    }

    private static HablarIconsBundle bundle;

    private static OldHablarIcons instance;

    public static HablarIconsBundle getBundle() {
	return bundle;
    }

    public static void setBundle(HablarIconsBundle bundle) {
	OldHablarIcons.bundle = bundle;
    }

    public static void setStyles(final OldHablarIcons oldHablarIcons) {
	instance = oldHablarIcons;
    }
}
