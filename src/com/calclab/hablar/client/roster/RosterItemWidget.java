package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RosterItemWidget extends Composite {

    private static RosterItemWidgetUiBinder uiBinder = GWT.create(RosterItemWidgetUiBinder.class);

    interface RosterItemWidgetUiBinder extends UiBinder<Widget, RosterItemWidget> {
    }

    @UiField
    Label name;
    private final RosterLogic logic;
    private final RosterItem item;

    public RosterItemWidget(RosterItem item, RosterLogic logic) {
	this.item = item;
	this.logic = logic;
	initWidget(uiBinder.createAndBindUi(this));
	name.setText(item.getName());
    }
    
    @UiHandler("name")
    void onClick(ClickEvent e) {
	logic.onItemClick(item);
    }

}
