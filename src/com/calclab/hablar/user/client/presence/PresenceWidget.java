package com.calclab.hablar.user.client.presence;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PresenceWidget extends Composite implements PresenceDisplay {

    interface PresenceWidgetUiBinder extends UiBinder<Widget, PresenceWidget> {
    }

    private static PresenceWidgetUiBinder uiBinder = GWT.create(PresenceWidgetUiBinder.class);

    @UiField
    TextBox status;

    public PresenceWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	status.ensureDebugId("PresenceWidget-status");

    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getStatus() {
	return status;
    }

}
