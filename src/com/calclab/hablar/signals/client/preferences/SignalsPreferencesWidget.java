package com.calclab.hablar.signals.client.preferences;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class SignalsPreferencesWidget extends Composite implements SignalsPreferencesDisplay {

    interface SignalsPreferencesWidgetUiBinder extends UiBinder<Widget, SignalsPreferencesWidget> {
    }

    private static SignalsPreferencesWidgetUiBinder uiBinder = GWT.create(SignalsPreferencesWidgetUiBinder.class);

    @UiField
    CheckBox titleSignals, incomingNotifications, rosterNotifications;

    public SignalsPreferencesWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasValue<Boolean> getIncomingNotifications() {
	return incomingNotifications;
    }

    @Override
    public HasValue<Boolean> getRosterNotifications() {
	return rosterNotifications;
    }

    @Override
    public HasValue<Boolean> getTitleSignals() {
	return titleSignals;
    }

}
