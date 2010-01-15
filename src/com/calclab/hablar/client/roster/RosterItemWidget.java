package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.icons.Icons;
import com.calclab.hablar.client.ui.icons.Icons.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
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
	sinkEvents(Event.ONCLICK | Event.ONDBLCLICK);
    }

    @Override
    public void onBrowserEvent(Event event) {
	int eventType = event.getTypeInt();
	if (eventType == Event.ONDBLCLICK) {
	} else if (eventType == Event.ONCLICK) {
	    if (event.getCurrentEventTarget().equals(name.getElement()))
		return;
	    final PopupPanel panel = new PopupPanel(true);
	    MenuBar bar = new MenuBar(true);
	    bar.addItem("Chat", true, new Command() {
		@Override
		public void execute() {
		    panel.hide();
		    logic.onItemClick(item);
		}
	    });
	    panel.add(bar);
	    panel.setPopupPosition(event.getClientX(), event.getClientY());
	    panel.show();
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
	this.iconStyle = Icons.get(iconType);
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

    @UiHandler("name")
    void onClick(ClickEvent e) {
	logic.onItemClick(item);
    }

}
