package com.calclab.hablar.client.ui.icons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Icons extends Composite {

    public static enum IconType {
	buddy, buddyDnd, buddyOff, buddyOn, buddyWait, roster
    }

    interface IconsUiBinder extends UiBinder<Widget, Icons> {
    }

    interface Styles extends CssResource {

	String buddyIcon();

	String buddyIconDnd();

	String buddyIconOff();

	String buddyIconOn();

	String buddyIconWait();

	String rosterIcon();
    }

    private static IconsUiBinder uiBinder = GWT.create(IconsUiBinder.class);

    @UiField
    Styles style;

    private static Icons instance;

    public static String get(IconType iconType) {
	if (instance == null) {
	    instance = new Icons();
	}
	return instance.getIconStyle(iconType);
    }

    Icons() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    private String getIconStyle(IconType iconType) {
	switch (iconType) {
	case buddy:
	    return style.buddyIcon();
	case buddyDnd:
	    return style.buddyIconDnd();
	case buddyOff:
	    return style.buddyIconOff();
	case buddyWait:
	    return style.buddyIconWait();
	case buddyOn:
	    return style.buddyIconOn();
	case roster:
	    return style.rosterIcon();
	}
	return null;
    }
}
