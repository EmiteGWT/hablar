package com.calclab.hablar.rooms.client.open;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SelectRosterItemWidget extends Composite implements SelectRosterItemDisplay {

	interface SelectRosterItemWidgetUiBinder extends UiBinder<Widget, SelectRosterItemWidget> {
	}

	private static SelectRosterItemWidgetUiBinder uiBinder = GWT.create(SelectRosterItemWidgetUiBinder.class);

	@UiField
	Label name, jid, status;

	@UiField
	CheckBox select;

	@UiField
	Image icon;

	public SelectRosterItemWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasText getJid() {
		return jid;
	}

	@Override
	public HasText getName() {
		return name;
	}

	@Override
	public HasValue<Boolean> getSelected() {
		return select;
	}

	@Override
	public HasText getStatus() {
		return status;
	}

	@Override
	public void setIcon(final ImageResource imageResource) {
		icon.setResource(imageResource);
	}

	@Override
	public void setSelectEnabled(final boolean enabled) {
		select.setEnabled(enabled);
	}

}
