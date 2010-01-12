package com.calclab.hablar.client.roster;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RosterItemWidget extends Composite {

    interface Icons extends CssResource {
	String buddyIcon();

	String buddyIconDnd();

	String buddyIconOff();

	String buddyIconOn();

	String buddyIconWait();
    }

    interface RosterItemWidgetUiBinder extends UiBinder<Widget, RosterItemWidget> {
    }

    private static RosterItemWidgetUiBinder uiBinder = GWT.create(RosterItemWidgetUiBinder.class);

    @UiField
    Label name, status, icon;

    @UiField
    Icons style;

    private final RosterLogic logic;
    private RosterItem item;

    private String iconStyle;

    public RosterItemWidget(RosterItem item, RosterLogic logic) {
	this.logic = logic;
	initWidget(uiBinder.createAndBindUi(this));
	setItem(item);
    }

    public void setItem(RosterItem item) {
	this.item = item;
	name.setText(item.getName());
	setShow(item);
	setStatus(item.getStatus());
    }

    private void setIcon(String iconStyle) {
	if (this.iconStyle != null) {
	    icon.getElement().removeClassName(this.iconStyle);
	}
	this.iconStyle = iconStyle;
	icon.getElement().addClassName(iconStyle);
    }

    private void setShow(RosterItem item) {
	Show show = item.getShow();
	GWT.log("ITEM SHOW: " + show + " > " + item.isAvailable(), null);
	if (show == Show.dnd) {
	    setIcon(style.buddyIconDnd());
	} else if (show == Show.xa) {
	    setIcon(style.buddyIconWait());
	} else if (show == Show.away) {
	    setIcon(style.buddyIconOff());
	} else if (item.isAvailable()) {
	    setIcon(style.buddyIconOn());
	} else {
	    setIcon(style.buddyIconOff());
	}
    }

    private void setStatus(String status) {
	if (status != null && status.trim().length() > 0) {
	    this.status.setText(status);
	} else {
	    this.status.setVisible(false);
	}
    }

    @UiHandler("name")
    void onClick(ClickEvent e) {
	logic.onItemClick(item);
    }

}
