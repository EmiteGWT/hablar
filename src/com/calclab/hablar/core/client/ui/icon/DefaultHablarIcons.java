package com.calclab.hablar.core.client.ui.icon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class DefaultHablarIcons extends OldHablarIcons {

    interface IconsUiBinder extends UiBinder<Widget, DefaultHablarIcons> {
    }

    private static IconsUiBinder uiBinder = GWT.create(IconsUiBinder.class);

    public DefaultHablarIcons() {
	uiBinder.createAndBindUi(this);
    }
}
