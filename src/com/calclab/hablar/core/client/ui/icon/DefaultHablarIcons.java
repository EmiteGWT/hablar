package com.calclab.hablar.core.client.ui.icon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class DefaultHablarIcons extends HablarIcons {

    interface IconsUiBinder extends UiBinder<Widget, DefaultHablarIcons> {
    }

    interface Styles extends CssResource {
	String active();

	String inactive();
    }

    private static IconsUiBinder uiBinder = GWT.create(IconsUiBinder.class);

    @UiField
    Styles style;

    public DefaultHablarIcons() {
	uiBinder.createAndBindUi(this);
    }
}
