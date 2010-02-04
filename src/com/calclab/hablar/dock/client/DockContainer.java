package com.calclab.hablar.dock.client;

import static com.google.gwt.dom.client.Style.Unit.PX;

import java.util.HashMap;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.HeaderPresenter;
import com.calclab.hablar.core.client.pages.PagesContainer;
import com.calclab.hablar.dock.client.DockConfig.Dock;
import com.calclab.hablar.dock.client.DockConfig.Position;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DockContainer implements PagesContainer {
    public static final String ROL = "RosterDock";
    private static final double HEADER_SIZE = 24;
    private final HashMap<Position, LayoutPanel> panels;
    private final HashMap<Position, Page<?>> pages;
    private final DockConfig config;

    public DockContainer(DockConfig config, Widget mainContainer, LayoutPanel parent) {
	this.config = config;
	panels = new HashMap<Position, LayoutPanel>();
	pages = new HashMap<Position, Page<?>>();

	layoutMainContainer(config, mainContainer, parent);

	for (Position pos : DockConfig.Position.values()) {
	    addDock(pos, config.get(pos), parent);
	}
	parent.forceLayout();
    }

    @Override
    public boolean add(Page<?> page) {
	for (Position position : DockConfig.Position.values()) {
	    if (page.getType().equals(config.get(position).pageType)) {
		pages.put(position, page);
		setPage(panels.get(position), page, position);
		return true;
	    }
	}
	return false;
    }

    @Override
    public boolean focus(Page<?> page) {
	if (page == pages.get(Position.top)) {
	    showTopPage(page);
	}
	return pages.values().contains(page);
    }

    @Override
    public String getRol() {
	return ROL;
    }

    @Override
    public Widget getWidget() {
	return null;
    }

    @Override
    public boolean hide(Page<?> page) {
	return pages.values().contains(page);
    }

    @Override
    public boolean remove(Page<?> page) {
	for (Position position : Position.values()) {
	    if (page == pages.get(position)) {
		pages.put(position, null);
		return true;
	    }
	}
	return false;
    }

    private void addDock(Position position, Dock dock, LayoutPanel parent) {
	LayoutPanel panel = new LayoutPanel();
	panels.put(position, panel);
	if (dock != null && dock.pageType != null) {
	    panel.addStyleName("hablar-RosterDockContainer-" + position);
	    panel.ensureDebugId("hablar-RosterDockContainer-" + position);
	    parent.add(panel);

	    Dock top = config.get(Position.top);
	    Dock bottom = config.get(Position.bottom);
	    Dock left = config.get(Position.left);
	    Dock right = config.get(Position.right);

	    if (position == Position.left) {
		parent.setWidgetTopBottom(panel, top.size, top.unit, bottom.size, bottom.unit);
		parent.setWidgetLeftWidth(panel, 0, PX, dock.size - 3, dock.unit);
	    } else if (position == Position.right) {
		parent.setWidgetTopBottom(panel, top.size, top.unit, bottom.size, bottom.unit);
		parent.setWidgetRightWidth(panel, 0, PX, dock.size - 3, dock.unit);
	    } else if (position == Position.top) {
		parent.setWidgetLeftRight(panel, 0, PX, 0, PX);
		parent.setWidgetTopHeight(panel, 0, PX, dock.size, dock.unit);
	    } else if (position == Position.bottom) {
		parent.setWidgetLeftRight(panel, 0, PX, 0, PX);
		parent.setWidgetBottomHeight(panel, 0, PX, dock.size, dock.unit);
	    }
	}
    }

    private void layoutMainContainer(DockConfig config, Widget mainContainer, LayoutPanel parent) {
	Dock top = config.get(Position.top);
	Dock bottom = config.get(Position.bottom);
	Dock left = config.get(Position.left);
	Dock right = config.get(Position.right);
	parent.setWidgetTopBottom(mainContainer, top.size, top.unit, bottom.size, bottom.unit);
	parent.setWidgetLeftRight(mainContainer, left.size, left.unit, right.size, right.unit);
    }

    private void setPage(LayoutPanel panel, Page<?> page, Position position) {
	Widget widget = page.getDisplay().asWidget();
	DockHeaderWidget headerWidget = new DockHeaderWidget(page.getId(), position);
	new HeaderPresenter(page.getState(), headerWidget);
	panel.add(headerWidget);
	panel.setWidgetTopHeight(headerWidget, 0, PX, HEADER_SIZE, PX);
	panel.setWidgetLeftRight(headerWidget, 0, PX, 0, PX);
	panel.add(widget);
	panel.setWidgetTopBottom(widget, HEADER_SIZE, PX, 0, PX);
	panel.setWidgetLeftRight(widget, 0, PX, 0, PX);
	panel.forceLayout();
    }

    private void showTopPage(Page<?> page) {
	// TODO Auto-generated method stub

    }

}
