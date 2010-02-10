package com.calclab.hablar.vcard.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class VCardWidget extends Composite implements VCardDisplay {

    interface VCardWidgetUiBinder extends UiBinder<Widget, VCardWidget> {
    }

    private static VCardWidgetUiBinder uiBinder = GWT.create(VCardWidgetUiBinder.class);

    public VCardWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
