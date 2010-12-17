package com.calclab.hablar.roster.client.groups;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RosterItemWidget extends Composite implements RosterItemDisplay {

    interface RosterItemWidgetUiBinder extends UiBinder<Widget, RosterItemWidget> {
    }

    @UiField
    FocusPanel self;

    @UiField
    Label name, jid, status;

    @UiField
    Image menu, icon;

    private static RosterItemWidgetUiBinder uiBinder = GWT.create(RosterItemWidgetUiBinder.class);

    public RosterItemWidget(final String groupId, final RosterItem item) {
	this(groupId, Idify.id(item.getJID()));
	jid.setText(item.getJID().toString());
	name.setText(item.getName());
	final String status = item.getStatus();
	final boolean hasStatus = (status != null) && (status.trim().length() > 0);
	if (hasStatus) {
	    this.status.setText(status);
	}
	this.status.setVisible(hasStatus);
	setIcon(PresenceIcon.get(item.isAvailable(), item.getShow()));
    }

    public RosterItemWidget(final String groupId, final String itemId) {
	initWidget(uiBinder.createAndBindUi(this));
	Icons.set(menu, Icons.MENU);
	self.ensureDebugId(Idify.id("RosterItemWidget", groupId, itemId));
	// menu.addStyleName(HablarIcons.get(IconType.menu));
	menu.ensureDebugId(Idify.id("RosterItemWidget", groupId, itemId, "roster-menu"));
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
    public void setColor(final String color) {
	DOM.setStyleAttribute(this.getElement(), "color", color);
    }

    @Override
    public void setIcon(final String token) {
	Icons.set(icon, token);
    }

    @Override
    public void setMenuVisible(final boolean visible) {
	menu.setVisible(visible);
    }

    @Override
    public void setStatusVisible(final boolean visible) {
	status.setVisible(visible);
    }

    @Override
    public void setWidgetTitle(final String title) {
	self.setTitle(title);
    }
}
