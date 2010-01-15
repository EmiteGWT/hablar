package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;
import com.calclab.hablar.client.ui.styles.HablarStyles.StyleType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

class RosterItemWidget extends Composite implements RosterItemView {

    interface RosterItemWidgetUiBinder extends UiBinder<Widget, RosterItemWidget> {
    }

    private static RosterItemWidgetUiBinder uiBinder = GWT.create(RosterItemWidgetUiBinder.class);

    @UiField
    Label name, status, icon, jid;

    private final RosterLogic logic;
    private RosterItem item;

    private String iconStyle;

    public RosterItemWidget(RosterLogic logic) {
	initWidget(uiBinder.createAndBindUi(this));
	this.logic = logic;
	sinkEvents(Event.ONCLICK | Event.ONMOUSEOVER | Event.ONMOUSEOUT);
    }

    @Override
    public void onBrowserEvent(Event event) {
	int eventType = event.getTypeInt();
	if (eventType == Event.ONMOUSEOVER) {
	    removeStyleName(HablarStyles.styleOf(StyleType.inactive));
	    addStyleName(HablarStyles.styleOf(StyleType.active));
	} else if (eventType == Event.ONMOUSEOUT) {
	    removeStyleName(HablarStyles.styleOf(StyleType.active));
	    addStyleName(HablarStyles.styleOf(StyleType.inactive));
	} else if (eventType == Event.ONCLICK) {
	    logic.onItemClick(item, event.getClientX(), event.getClientY());
	} else {
	    super.onBrowserEvent(event);
	}
    }

    @Override
    public void setIcon(IconType iconType) {
	if (this.iconStyle != null) {
	    icon.getElement().removeClassName(iconStyle);
	    // icon.removeStyleName(iconStyle);
	}
	this.iconStyle = HablarStyles.get(iconType);
	icon.getElement().addClassName(iconStyle);
	// icon.addStyleName(iconStyle);
    }

    @Override
    public void setItem(RosterItem item) {
	this.item = item;
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

}
