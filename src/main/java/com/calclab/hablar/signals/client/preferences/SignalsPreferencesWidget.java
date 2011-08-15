package com.calclab.hablar.signals.client.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class SignalsPreferencesWidget extends Composite implements SignalsPreferencesDisplay {

	interface SignalsPreferencesWidgetUiBinder extends UiBinder<Widget, SignalsPreferencesWidget> {
	}

	private static SignalsPreferencesWidgetUiBinder uiBinder = GWT.create(SignalsPreferencesWidgetUiBinder.class);

	@UiField
	CheckBox titleSignals, incomingNotifications, rosterNotifications;

	@UiField
	HasText loadingMessage;
	@UiField
	DivElement form, loading;
	@UiField
	Image icon;
	@UiField
	FlowPanel methodsPanel;

	HashMap<String, CheckBox> notifierMap;

	public SignalsPreferencesWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		titleSignals.ensureDebugId("SignalsPreferencesWidget-titleSignals");
		incomingNotifications.ensureDebugId("SignalsPreferencesWidget-incomingNotifications");
		rosterNotifications.ensureDebugId("SignalsPreferencesWidget-rosterNotifications");
		notifierMap = new HashMap<String, CheckBox>();
	}

	@Override
	public void addNotifier(final String id, final String name) {
		final CheckBox notifierCheckbox = new CheckBox(name);
		notifierCheckbox.ensureDebugId("hablarNotifierPreferenceEnabled-" + id);

		notifierMap.put(id, notifierCheckbox);

		methodsPanel.add(notifierCheckbox);
	}

	@Override
	public HasValue<Boolean> getIncomingNotifications() {
		return incomingNotifications;
	}

	@Override
	public HasText getLoading() {
		return loadingMessage;
	}

	@Override
	public HasValue<Boolean> getRosterNotifications() {
		return rosterNotifications;
	}

	@Override
	public List<String> getSelectedNotifiers() {
		final ArrayList<String> ret = new ArrayList<String>();

		for (final Entry<String, CheckBox> entry : notifierMap.entrySet()) {
			if (entry.getValue().getValue()) {
				ret.add(entry.getKey());
			}
		}

		return ret;
	}

	@Override
	public HasValue<Boolean> getTitleSignals() {
		return titleSignals;
	}

	@Override
	public boolean isNotifierSelected(final String id) {
		final CheckBox checkbox = notifierMap.get(id);

		if (checkbox != null) {
			return checkbox.getValue();
		}

		return false;
	}

	@Override
	public void setFormVisible(final boolean visible) {
		setVisible(form, visible);
	}

	@Override
	public void setLoadingVisible(final boolean visible) {
		setVisible(loading, visible);
	}

	@Override
	public void setNotifierSelected(final String id, final boolean selected) {
		final CheckBox checkbox = notifierMap.get(id);

		if (checkbox != null) {
			checkbox.setValue(selected);
		}
	}
}
