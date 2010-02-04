package com.calclab.hablar.dock.client;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.PagesContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DockContainer implements PagesContainer {
    public static final String ROL = "RosterDock";
    private LayoutPanel leftPanel;
    private LayoutPanel rightPanel;
    private Page<?> leftPage, rightPage;
    private final DockConfig config;

    public DockContainer(DockConfig config, Widget mainContainer, LayoutPanel parent) {
	this.config = config;
	parent.setWidgetLeftRight(mainContainer, config.leftSize, PX, config.rightSize, PX);
	if (config.leftType != null) {
	    this.leftPanel = new LayoutPanel();
	    leftPanel.addStyleName("hablar-RosterDockContainer-left");
	    leftPanel.ensureDebugId("hablar-RosterDockContainer-left");
	    parent.add(leftPanel);
	    parent.setWidgetTopBottom(leftPanel, 0, PX, 0, PX);
	    parent.setWidgetLeftWidth(leftPanel, 0, PX, config.leftSize, PX);
	}
	if (config.rightType != null) {
	    this.rightPanel = new LayoutPanel();
	    rightPanel.addStyleName("hablar-RosterDockContainer-right");
	    rightPanel.ensureDebugId("hablar-RosterDockContainer-right");
	    parent.add(rightPanel);
	    parent.setWidgetTopBottom(rightPanel, 0, PX, 0, PX);
	    parent.setWidgetRightWidth(rightPanel, 0, PX, config.rightSize, PX);
	}
	parent.forceLayout();
    }

    @Override
    public boolean add(Page<?> page) {
	if (page.getType().equals(config.leftType) && leftPage == null) {
	    GWT.log("Add LEFT DOCK", null);
	    leftPage = page;
	    setPage(leftPanel, leftPage);
	    return true;
	} else if (page.getType().equals(config.rightType) && rightPage == null) {
	    rightPage = page;
	    setPage(rightPanel, rightPage);
	    return true;
	}
	return false;
    }

    @Override
    public boolean focus(Page<?> page) {
	return false;
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
	if (leftPage == page || rightPage == page)
	    return true;
	else
	    return false;
    }

    @Override
    public boolean remove(Page<?> page) {
	if (leftPage == page) {
	    leftPage = null;
	    return true;
	} else if (rightPage == page) {
	    rightPage = null;
	    return true;
	}
	return false;
    }

    private void setPage(LayoutPanel panel, Page<?> page) {
	Widget widget = page.getDisplay().asWidget();
	panel.add(widget);
	panel.setWidgetTopBottom(widget, 0, PX, 0, PX);
	panel.setWidgetLeftRight(widget, 0, PX, 3, PX);
	panel.forceLayout();
    }

}
