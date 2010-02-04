package com.calclab.hablar.roster.client;

import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RosterItemWidget extends Composite implements RosterItemDisplay {

    interface RosterItemWidgetUiBinder extends UiBinder<Widget, RosterItemWidget> {
    }

    @UiField
    FocusPanel self;

    @UiField
    Label icon, name, jid, menu, status;

    private String currentStyle;

    private static RosterItemWidgetUiBinder uiBinder = GWT.create(RosterItemWidgetUiBinder.class);

    public RosterItemWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	menu.addStyleName(HablarIcons.get(IconType.menu));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getAction() {
	return self;
    }

    @Override
    public HasText getJid() {
	return jid;
    }

    @Override
    public HasClickHandlers getMenuAction() {
	return menu;
    }

    @Override
    public HasText getName() {
	return name;
    }

    @Override
    public HasText getStatus() {
	return status;
    }

    @Override
    public void setIcon(String iconStyle) {
	if (currentStyle != null) {
	    icon.removeStyleName(iconStyle);
	}
	currentStyle = iconStyle;
	icon.addStyleName(iconStyle);
    }

    @Override
    public void setStatusVisible(boolean visible) {
	status.setVisible(visible);
    }

}
