package com.calclab.hablar.client.roster;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

class RosterItemWidget extends Composite implements RosterItemView {

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
    Label name, status, icon, jid;

    @UiField
    Icons style;

    private final RosterLogic logic;
    private RosterItem item;

    private String iconStyle;

    public RosterItemWidget(RosterItem item, RosterLogic logic) {
	this.logic = logic;
	initWidget(uiBinder.createAndBindUi(this));
	setItem(item);
	sinkEvents(Event.ONCLICK | Event.ONDBLCLICK);
    }

    @Override
    public void onBrowserEvent(Event event) {
	int eventType = event.getTypeInt();
	if (eventType == Event.ONDBLCLICK) {
	    Window.alert("Abre!");
	} else if (eventType == Event.ONCLICK) {
	    Window.alert("Pincha!");
	} else {
	    super.onBrowserEvent(event);
	}
    }

    public void setItem(RosterItem item) {
	GWT.log("ITEM: " + item.getName() + ":" + item.getJID(), null);
	this.item = item;
	name.setText(item.getName());
	jid.setText(item.getJID().toString());
	setShow(item);
	setStatus(item.getStatus());
	sinkEvents(Event.ONCLICK);
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
