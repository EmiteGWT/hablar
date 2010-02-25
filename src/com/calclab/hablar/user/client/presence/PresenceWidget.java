package com.calclab.hablar.user.client.presence;

import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.core.client.ui.menu.PopupMenu;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PresenceWidget extends Composite implements PresenceDisplay {

    interface PresenceWidgetUiBinder extends UiBinder<Widget, PresenceWidget> {
    }

    private static PresenceWidgetUiBinder uiBinder = GWT.create(PresenceWidgetUiBinder.class);

    @UiField
    TextBox status;
    @UiField
    Label menu, icon;
    private String currentStyle;

    public PresenceWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	status.ensureDebugId("PresenceWidget-status");
	menu.addStyleName(HablarIcons.get(IconType.menu));
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
    public void setStatusIcon(final String iconStyle) {
	if (currentStyle != null) {
	    icon.removeStyleName(currentStyle);
	}
	currentStyle = iconStyle;
	icon.addStyleName(iconStyle);
    }

}
