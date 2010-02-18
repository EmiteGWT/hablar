package com.calclab.hablar.signals.client.preferences;

import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
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

    @UiField
    SpanElement icon;
    @UiField
    DivElement form, loading;

    public SignalsPreferencesWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	icon.addClassName(HablarIcons.get(IconType.loading));
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

    @Override
    public void setFormVisible(final boolean visible) {
	setVisible(form, visible);
    }

    @Override
    public void setLoadingVisible(final boolean visible) {
	setVisible(loading, visible);
    }

}
