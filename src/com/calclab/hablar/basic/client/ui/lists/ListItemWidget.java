package com.calclab.hablar.basic.client.ui.lists;

import com.calclab.hablar.basic.client.ui.icons.HablarIcons;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public abstract class ListItemWidget extends Composite implements ListItemView {
    protected final ListLogic logic;
    private Label menu;

    public ListItemWidget(ListLogic logic) {
	this.logic = logic;
	sinkEvents(Event.ONCLICK | Event.ONMOUSEOVER | Event.ONMOUSEOUT);
    }

    @Override
    public void onBrowserEvent(Event event) {
	int eventType = event.getTypeInt();
	if (eventType == Event.ONMOUSEOVER) {
	    logic.onMouseOver(this, true);
	} else if (eventType == Event.ONMOUSEOUT) {
	    logic.onMouseOver(this, false);
	} else if (eventType == Event.ONCLICK) {
	    logic.onItemClick(this, event);
	} else {
	    super.onBrowserEvent(event);
	}
    }

    public void setMenu(final Label menu) {
	this.menu = menu;
	menu.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		event.stopPropagation();
		logic.onMenuClicked(ListItemWidget.this, menu);
	    }
	});
	setMenuVisible(false);
    }

    @Override
    public void setMenuVisible(boolean visible) {
	menu.setVisible(visible);
    }

    @Override
    public void setSelected(boolean active) {
	if (active) {
	    removeStyleName(HablarIcons.styleOf(HablarIcons.StyleType.inactive));
	    addStyleName(HablarIcons.styleOf(HablarIcons.StyleType.active));
	} else {
	    removeStyleName(HablarIcons.styleOf(HablarIcons.StyleType.active));
	    addStyleName(HablarIcons.styleOf(HablarIcons.StyleType.inactive));
	}
    }
}
