package com.calclab.hablar.client.ui.icons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Icons extends Composite {

    public static enum IconType {
	buddy, buddyDnd, buddyOff, buddyOn, buddyWait
    }

    interface IconsUiBinder extends UiBinder<Widget, Icons> {
    }

    interface Styles extends CssResource {

	String buddyIcon();

	String buddyIconDnd();

	String buddyIconOff();

	String buddyIconOn();

	String buddyIconWait();

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
	if (iconType == IconType.buddy)
	    return style.buddyIcon();
	else if (iconType == IconType.buddyDnd)
	    return style.buddyIconDnd();
	else if (iconType == IconType.buddyOn)
	    return style.buddyIconOn();
	else if (iconType == IconType.buddyOff)
	    return style.buddyIconOff();
	else if (iconType == IconType.buddyWait)
	    return style.buddyIconWait();
	return null;
    }
}
