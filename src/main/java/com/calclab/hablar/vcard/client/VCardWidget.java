package com.calclab.hablar.vcard.client;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class VCardWidget extends Composite implements VCardDisplay {

	interface VCardWidgetUiBinder extends UiBinder<Widget, VCardWidget> {
	}

	protected static VCardWidgetUiBinder uiBinder = GWT.create(VCardWidgetUiBinder.class);

	@UiField
	TextBox name, nickName, givenName, middleName, familyName, email, organizationName, organizationUnit, homepage;
	@UiField
	Button accept, cancel;
	@UiField
	SpanElement title;
	@UiField
	HasText loadingMessage;
	@UiField
	DivElement form, loading;

	@UiField
	Image icon;
	protected final HashMap<Field, TextBox> fields = new HashMap<Field, TextBox>();

	public VCardWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public VCardWidget(final boolean readOnly, final String idPrefix) {
		this();
		init(readOnly, idPrefix);
	}

	@Override
	public HasClickHandlers getCancel() {
		return cancel;
	}

	@Override
	public HasText getField(final Field field) {
		return fields.get(field);
	}

	@Override
	public HasText getLoading() {
		return loadingMessage;
	}

	@Override
	public void setAcceptVisible(final boolean visible) {
		accept.setVisible(visible);
	}

	@Override
	public void setCancelText(final String text) {
		cancel.setText(text);
	}

	@Override
	public void setCancelVisible(final boolean visible) {
		cancel.setVisible(visible);
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
	public void setPageTitle(final String titleText) {
		title.setInnerText(titleText);
	}

	public void setReadOnly(final boolean readOnly) {
		for (final TextBox textbox : fields.values()) {
			textbox.setEnabled(!readOnly);
		}
		if (readOnly) {
			addStyleName("readOnly");
		} else {
			removeStyleName("readOnly");
		}
	}

	protected void init(final boolean readOnly, final String idPrefix) {
		fields.put(Field.name, name);
		fields.put(Field.nickName, nickName);
		fields.put(Field.middleName, middleName);
		fields.put(Field.givenName, givenName);
		fields.put(Field.familyName, familyName);
		fields.put(Field.email, email);
		fields.put(Field.homepage, homepage);
		fields.put(Field.organizationName, organizationName);
		fields.put(Field.organizationUnit, organizationUnit);
		for (final Field field : fields.keySet()) {
			fields.get(field).ensureDebugId(idPrefix + "-" + field.toString());
		}

		setReadOnly(readOnly);
	}
}
