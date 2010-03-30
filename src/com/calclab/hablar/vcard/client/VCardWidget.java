package com.calclab.hablar.vcard.client;

import java.util.HashMap;

import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class VCardWidget extends Composite implements VCardDisplay {

    private static VCardMessages messages;

    public static void setMessages(final VCardMessages messages) {
	VCardWidget.messages = messages;
    }

    public static VCardMessages i18n() {
	return messages;
    }

    interface VCardWidgetUiBinder extends UiBinder<Widget, VCardWidget> {
    }

    protected static VCardWidgetUiBinder uiBinder = GWT.create(VCardWidgetUiBinder.class);

    @UiField
    TextBox name, nickName, givenName, middleName, familyName, email, organizationName, homepage;
    @UiField
    Button accept, cancel;
    @UiField
    SpanElement title, icon;
    @UiField
    Label loadingMessage;
    @UiField
    DivElement form, loading;
    @UiField
    LabelElement nameLabel, nicknameLabel, familyNameLabel, middleNameLabel, givenNameLabel,
    		organizationLabel, homepageLabel, emailLabel;

    protected final HashMap<Field, TextBox> fields = new HashMap<Field, TextBox>();

    public VCardWidget() {
    }

    public VCardWidget(final boolean readOnly, final String idPrefix) {
	initWidget(uiBinder.createAndBindUi(this));
	initI18n();
	init(readOnly, idPrefix);
    }

    @Override
    public Widget asWidget() {
	return this;
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
	icon.addClassName(HablarIcons.get(IconType.loading));
	fields.put(Field.name, name);
	fields.put(Field.nickName, nickName);
	fields.put(Field.middleName, middleName);
	fields.put(Field.givenName, givenName);
	fields.put(Field.familyName, familyName);
	fields.put(Field.email, email);
	fields.put(Field.homepage, homepage);
	fields.put(Field.organizationName, organizationName);
	for (final Field field : fields.keySet()) {
	    fields.get(field).ensureDebugId(idPrefix + "-" + field.toString());
	}

	setReadOnly(readOnly);
    }

    protected void initI18n() {
	nameLabel.setInnerText(i18n().nameLabelText());
	nicknameLabel.setInnerText(i18n().nicknameLabelText());
	familyNameLabel.setInnerText(i18n().familyNameLabelText());
	middleNameLabel.setInnerText(i18n().middleNameLabelText());
	givenNameLabel.setInnerText(i18n().givenNameLabelText());
	organizationLabel.setInnerText(i18n().organizationLabelText());
	homepageLabel.setInnerText(i18n().homepageLabelText());
	emailLabel.setInnerText(i18n().emailLabelText());
    }
}
