package com.calclab.hablar.core.client.ui.icon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

/**
 * Extend the DefaultHablarIcons class to use jpg (yuck) icons
 * as IE6 seems to have a some issues with png background images
 */
public class DefaultHablarIconsIE6 extends DefaultHablarIcons {

    interface IconsUiBinder extends UiBinder<Widget, DefaultHablarIconsIE6> {
    }

    private static IconsUiBinder uiBinder = GWT.create(IconsUiBinder.class);

    public DefaultHablarIconsIE6() {
	uiBinder.createAndBindUi(this);
    }
}
