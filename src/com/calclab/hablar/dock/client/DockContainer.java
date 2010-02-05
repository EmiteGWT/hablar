package com.calclab.hablar.dock.client;

import static com.google.gwt.dom.client.Style.Unit.PCT;
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
    private final LayoutPanel parent;
    private final Widget center;
    private boolean isTopFocused;

    public DockContainer(DockConfig config, Widget center, LayoutPanel parent) {
	this.config = config;
	this.center = center;
	this.parent = parent;
	panels = new HashMap<Position, LayoutPanel>();
	pages = new HashMap<Position, Page<?>>();

	layoutCenter();

	for (Position pos : DockConfig.Position.values()) {
	    addDock(pos, config.get(pos));
	}
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

    @Override
    public boolean unfocus(Page<?> page) {
	if (page == pages.get(Position.top)) {
	    hideTopPage(page);
	    return false;
	}
	return true;
    }

    private void addDock(Position position, Dock dock) {
	LayoutPanel panel = new LayoutPanel();
	panels.put(position, panel);
	parent.add(panel);

	panel.addStyleName("hablar-RosterDockContainer-" + position);
	panel.ensureDebugId("hablar-RosterDockContainer-" + position);

	Dock top = config.get(Position.top);
	Dock bottom = config.get(Position.bottom);

	layoutPanel(panel, position, dock, top, bottom);
    }

    private void hideTopPage(Page<?> page) {
	Dock dock = config.get(Position.top);
	LayoutPanel panel = panels.get(Position.top);
	parent.setWidgetTopHeight(panel, 0, PX, 100, PCT);
	parent.forceLayout();
	parent.setWidgetTopHeight(panel, 0, PX, dock.size, dock.unit);
	parent.animate(500);
	isTopFocused = false;

    }

    private void layoutCenter() {
	Dock top = config.get(Position.top);
	Dock bottom = config.get(Position.bottom);
	Dock left = config.get(Position.left);
	Dock right = config.get(Position.right);
	parent.setWidgetTopBottom(center, top.size, top.unit, bottom.size, bottom.unit);
	parent.setWidgetLeftRight(center, left.size, left.unit, right.size, right.unit);
    }

    private void layoutPanel(LayoutPanel panel, Position position, Dock dock, Dock top, Dock bottom) {
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

    private void setPage(LayoutPanel panel, Page<?> page, Position position) {
	Widget widget = page.getDisplay().asWidget();
	DockHeaderWidget headerWidget = new DockHeaderWidget(page.getId(), position);
	new HeaderPresenter(page, headerWidget);
	panel.add(headerWidget);
	panel.setWidgetTopHeight(headerWidget, 0, PX, HEADER_SIZE, PX);
	panel.setWidgetLeftRight(headerWidget, 0, PX, 0, PX);
	panel.add(widget);
	panel.setWidgetTopBottom(widget, HEADER_SIZE, PX, 0, PX);
	panel.setWidgetLeftRight(widget, 0, PX, 0, PX);
    }

    private void showTopPage(Page<?> page) {
	Dock dock = config.get(Position.top);
	LayoutPanel panel = panels.get(Position.top);
	parent.setWidgetTopHeight(panel, 0, PX, dock.size, dock.unit);
	parent.forceLayout();
	parent.setWidgetTopHeight(panel, 0, PX, 100, PCT);
	parent.animate(500);
	isTopFocused = true;
    }

}
