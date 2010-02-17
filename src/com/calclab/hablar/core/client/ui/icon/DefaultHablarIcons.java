package com.calclab.hablar.core.client.ui.icon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class DefaultHablarIcons extends HablarIcons {

    interface IconsUiBinder extends UiBinder<Widget, DefaultHablarIcons> {
    }

    interface StyleIcons extends CssResource {

	String buddyAddIcon();

	String buddyIcon();

	String buddyIconDnd();

	String buddyIconOff();

	String buddyIconOn();

	String buddyIconWait();

	String chatAddIcon();

	String chatIcon();

	String closeIcon();

	String consoleIcon();

	String loadingIcon();

	String menuIcon();

	String offIcon();

	String onIcon();

	String rosterIcon();

	String searchIcon();
    }

    interface Styles extends CssResource {
	String active();

	String inactive();
    }

    private static IconsUiBinder uiBinder = GWT.create(IconsUiBinder.class);

    @UiField
    StyleIcons icons;
    @UiField
    Styles style;

    public DefaultHablarIcons() {
	uiBinder.createAndBindUi(this);
    }

    @Override
    public String getIconStyle(final HablarIcons.IconType iconType) {
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
	case on:
	    return icons.onIcon();
	case off:
	    return icons.offIcon();
	case close:
	    return icons.closeIcon();
	case buddyAdd:
	    return icons.buddyAddIcon();
	case console:
	    return icons.consoleIcon();
	case loading:
	    return icons.loadingIcon();
	}
	return null;
    }

    @Override
    public String getStyleOf(final HablarIcons.StyleType type) {
	switch (type) {
	case active:
	    return style.active();
	case inactive:
	    return style.inactive();
	}
	return null;
    }
}
