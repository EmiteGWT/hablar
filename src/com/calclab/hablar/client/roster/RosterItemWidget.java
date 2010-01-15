package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

class RosterItemWidget extends Composite implements RosterItemView {

    interface RosterItemWidgetUiBinder extends UiBinder<Widget, RosterItemWidget> {
    }

    private static RosterItemWidgetUiBinder uiBinder = GWT.create(RosterItemWidgetUiBinder.class);

    @UiField
    Label name, status, icon, jid;

    @UiField
    RosterItemIcons style;

    private final RosterLogic logic;
    private RosterItem item;

    private String iconStyle;

    public RosterItemWidget(RosterLogic logic) {
	initWidget(uiBinder.createAndBindUi(this));
	this.logic = logic;
	sinkEvents(Event.ONCLICK | Event.ONDBLCLICK);
    }

    @Override
    public RosterItemIcons getIconStyles() {
	return style;
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

    @Override
    public void setIcon(String iconStyle) {
	if (this.iconStyle != null) {
	    icon.getElement().removeClassName(this.iconStyle);
	}
	this.iconStyle = iconStyle;
	icon.getElement().addClassName(iconStyle);
    }

    @Override
    public void setJID(String jidString) {
	jid.setText(jidString);
    }

    @Override
    public void setName(String nameString) {
	name.setText(nameString);
    }

    @Override
    public void setStatus(String status) {
	this.status.setText(status);
    }

    @Override
    public void setStatusVisible(boolean visible) {
	this.status.setVisible(visible);
    }

    @UiHandler("name")
    void onClick(ClickEvent e) {
	logic.onItemClick(item);
    }

}
