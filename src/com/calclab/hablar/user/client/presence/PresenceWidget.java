package com.calclab.hablar.user.client.presence;

import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.core.client.ui.menu.PopupMenu;
import com.calclab.hablar.user.client.UserMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PresenceWidget extends Composite implements PresenceDisplay {

    private static UserMessages messages;

    public static void setMessages(final UserMessages messages) {
	PresenceWidget.messages = messages;
    }

    public static UserMessages i18n() {
	return messages;
    }

    interface PresenceWidgetUiBinder extends UiBinder<Widget, PresenceWidget> {
    }

    private static PresenceWidgetUiBinder uiBinder = GWT.create(PresenceWidgetUiBinder.class);

    @UiField
    TextBox status;
    @UiField
    SpanElement title;
    @UiField
    LabelElement statusLabel;
    @UiField
    Image icon, menu;

    public PresenceWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	status.ensureDebugId("PresenceWidget-status");
	menu.addStyleName("PresenceWidget-menu");
	statusLabel.setInnerText(i18n().statusLabelText());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getIcon() {
	return icon;
    }

    @Override
    public HasClickHandlers getMenu() {
	return menu;
    }

    @Override
    public HasKeyDownHandlers getStatus() {
	return status;
    }

    @Override
    public HasBlurHandlers getStatusFocus() {
	return status;
    }

    @Override
    public HasText getStatusText() {
	return status;
    }

    @Override
    public MenuDisplay<PresencePage> newStatusMenuDisplay(final String menuId) {
	return new PopupMenu<PresencePage>(menuId);
    }

    @Override
    public void setStatusEnabled(final boolean enabled) {
	status.setEnabled(enabled);
    }

    @Override
    public void setStatusFocused(final boolean focused) {
	status.setFocus(focused);
    }

    @Override
    public void setStatusIcon(final ImageResource icon) {
	this.icon.setResource(icon);
    }

    @Override
    public void setPageTitle(String title) {
	this.title.setInnerText(title);
    }
}
