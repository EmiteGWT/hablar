package com.calclab.hablar.dock.client;

import static com.google.gwt.dom.client.Style.Unit.PCT;
import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.HablarDisplay;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.dock.client.DockConfig.Dock;
import com.calclab.hablar.dock.client.DockConfig.Position;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DockLayout {

    private final HablarDisplay display;
    private final Widget center;

    public DockLayout(Widget center, HablarDisplay display) {
	this.center = center;
	this.display = display;
    }

    public LayoutPanel addNewPanel(String debugId) {
	LayoutPanel panel = new LayoutPanel();
	panel.ensureDebugId(debugId);
	display.add(panel);
	return panel;
    }

    public HeaderDisplay createHeaderWidget(Page<?> page, Position position) {
	DockHeaderWidget headerWidget = new DockHeaderWidget(page.getId(), position);
	return headerWidget;
    }

    public void layoutPanel(LayoutPanel panel, Position position, Dock dock, Dock top, Dock bottom) {
	if (position == Position.left) {
	    display.setWidgetTopBottom(panel, top.size, top.unit, bottom.size, bottom.unit);
	    display.setWidgetLeftWidth(panel, 0, PX, dock.size - 3, dock.unit);
	} else if (position == Position.right) {
	    display.setWidgetTopBottom(panel, top.size, top.unit, bottom.size, bottom.unit);
	    display.setWidgetRightWidth(panel, 0, PX, dock.size - 3, dock.unit);
	} else if (position == Position.top) {
	    display.setWidgetLeftRight(panel, 0, PX, 0, PX);
	    display.setWidgetTopHeight(panel, 0, PX, dock.size, dock.unit);
	} else if (position == Position.bottom) {
	    display.setWidgetLeftRight(panel, 0, PX, 0, PX);
	    display.setWidgetBottomHeight(panel, 0, PX, dock.size, dock.unit);
	}
    }

    public void setCenterPosition(Dock left, Dock top, Dock right, Dock bottom) {
	display.setWidgetTopBottom(center, top.size, top.unit, bottom.size, bottom.unit);
	display.setWidgetLeftRight(center, left.size, left.unit, right.size, right.unit);
    }

    public void slideDown(Widget panel, Dock dock) {
	display.setWidgetTopHeight(panel, 0, PX, dock.size, dock.unit);
	display.forceLayout();
	display.setWidgetTopHeight(panel, 0, PX, 100, PCT);
	display.animate(500);
    }

    public void slideUp(Widget panel, Dock dock) {
	display.setWidgetTopHeight(panel, 0, PX, 100, PCT);
	display.forceLayout();
	display.setWidgetTopHeight(panel, 0, PX, dock.size, dock.unit);
	display.animate(500);
    }

}
