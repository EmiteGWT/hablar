package com.calclab.hablar.client.ui.styles;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HablarStyles extends Composite {

    public static enum IconType {
	buddy, buddyDnd, buddyOff, buddyOn, buddyWait, roster
    }

    public static enum StyleType {
	active, inactive
    }

    interface IconsUiBinder extends UiBinder<Widget, HablarStyles> {
    }

    interface StyleIcons extends CssResource {

	String buddyIcon();

	String buddyIconDnd();

	String buddyIconOff();

	String buddyIconOn();

	String buddyIconWait();

	String rosterIcon();
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

    private static HablarStyles instance = new HablarStyles();;

    public static String get(IconType iconType) {
	return instance.getIconStyle(iconType);
    }

    public static String styleOf(StyleType type) {
	return instance.getStyleOf(type);
    }

    HablarStyles() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    private String getIconStyle(IconType iconType) {
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
	}
	return null;
    }

    private String getStyleOf(StyleType type) {
	switch (type) {
	case active:
	    return style.active();
	case inactive:
	    return style.inactive();
	}
	return null;
    }
}
