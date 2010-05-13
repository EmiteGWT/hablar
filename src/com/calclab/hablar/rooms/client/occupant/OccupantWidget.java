package com.calclab.hablar.rooms.client.occupant;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class OccupantWidget extends Composite implements OccupantDisplay {

    interface OccupantWidgetUiBinder extends UiBinder<Widget, OccupantWidget> {
    }

    private static OccupantWidgetUiBinder uiBinder = GWT.create(OccupantWidgetUiBinder.class);

    @UiField
    Label name;

    @UiField
    Image icon;

    public OccupantWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getName() {
	return name;
    }

    @Override
    public void setIcon(final ImageResource icon) {
	this.icon.setResource(icon);
    }

}
