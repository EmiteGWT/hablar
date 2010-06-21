package com.calclab.hablar.dock.client;

import static com.google.gwt.dom.client.Style.Unit.*;

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

    public DockLayout(final Widget center, final HablarDisplay display) {
	this.center = center;
	this.display = display;
    }

    public LayoutPanel addNewPanel(final String debugId) {
	final LayoutPanel panel = new LayoutPanel();
	panel.ensureDebugId(debugId);
	display.add(panel);
	return panel;
    }

    public HeaderDisplay createHeaderWidget(final Page<?> page, final Position position) {
	final DockHeaderWidget headerWidget = new DockHeaderWidget(page.getId(), position);
	return headerWidget;
    }

    public void layoutPanel(final LayoutPanel panel, final Position position, final Dock dock, final Dock top,
	    final Dock bottom) {
	if (position == Position.left) {
	    display.setWidgetTopBottom(panel, top.size, top.unit, bottom.size, bottom.unit);
	    display.setWidgetLeftWidth(panel, 0, PX, Math.max(dock.size - 3, 0), dock.unit);
	} else if (position == Position.right) {
	    display.setWidgetTopBottom(panel, top.size, top.unit, bottom.size, bottom.unit);
	    display.setWidgetRightWidth(panel, 0, PX, Math.max(dock.size - 3, 0), dock.unit);
	} else if (position == Position.top) {
	    display.setWidgetLeftRight(panel, 0, PX, 0, PX);
	    display.setWidgetTopHeight(panel, 0, PX, dock.size, dock.unit);
	} else if (position == Position.bottom) {
	    display.setWidgetLeftRight(panel, 0, PX, 0, PX);
	    display.setWidgetBottomHeight(panel, 0, PX, dock.size, dock.unit);
	}
    }

    public void setCenterPosition(final Dock left, final Dock top, final Dock right, final Dock bottom) {
	display.setWidgetTopBottom(center, top.size, top.unit, bottom.size, bottom.unit);
	display.setWidgetLeftRight(center, left.size, left.unit, right.size, right.unit);
    }

    public void slideDown(final Widget panel, final Dock dock) {
	display.setWidgetTopHeight(panel, 0, PX, dock.size, dock.unit);
	display.forceLayout();
	display.setWidgetTopBottom(panel, 0, PX, 0, PX);
	display.animate(500);
    }

    public void slideUp(final Widget panel, final Dock dock) {
	display.setWidgetTopBottom(panel, 0, PX, 0, PX);
	display.forceLayout();
	display.setWidgetTopHeight(panel, 0, PX, dock.size, dock.unit);
	display.animate(500);
    }

}
