package com.calclab.hablar.client.ui.styles;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class DefaultHablarStyles extends HablarStyles {

    interface IconsUiBinder extends UiBinder<Widget, DefaultHablarStyles> {
    }

    interface StyleIcons extends CssResource {

	String buddyIcon();

	String buddyIconDnd();

	String buddyIconOff();

	String buddyIconOn();

	String buddyIconWait();

	String chatAddIcon();

	String chatIcon();

	String menuIcon();

	String rosterIcon();

	String searchIcon();
    }

    interface Styles extends CssResource {
	String active();

	String inactive();
    }

    private static IconsUiBinder uiBinder = GWT.create(IconsUiBinder.class);

    public static void init() {
	HablarStyles.setStyles(new DefaultHablarStyles());
    }

    @UiField
    StyleIcons icons;
    @UiField
    Styles style;

    DefaultHablarStyles() {
	uiBinder.createAndBindUi(this);
    }

    @Override
    public String getIconStyle(HablarStyles.IconType iconType) {
	switch (iconType) {
	case buddy:
	    return icons.buddyIcon();
	case buddyDnd:
	    return icons.buddyIconDnd();
	case buddyOff:
	    return icons.buddyIconOff();
	case buddyWait:
	    return icons.buddyIconWait();
	case buddyOn:
	    return icons.buddyIconOn();
	case roster:
	    return icons.rosterIcon();
	case menu:
	    return icons.menuIcon();
	case chat:
	    return icons.chatIcon();
	case chatAdd:
	    return icons.chatAddIcon();
	case search:
	    return icons.searchIcon();
	}
	return null;
    }

    @Override
    public String getStyleOf(HablarStyles.StyleType type) {
	switch (type) {
	case active:
	    return style.active();
	case inactive:
	    return style.inactive();
	}
	return null;
    }
}
