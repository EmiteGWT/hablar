/*
 * Copyright 2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.calclab.hablar.core.client.pages.accordion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

/**
 * A regular GWT StackLayoutPanel but with some improvements.
 * 
 * 1) Fire events when the header are clicked, but do not open them<br/>
 * 2) Remove widgets correctly (see
 * http://code.google.com/p/google-web-toolkit/issues/detail?id=4174)
 * 
 */
public class AccordionPanel extends Composite implements HasWidgets, RequiresResize, ProvidesResize {

    private static class LayoutData {
	public double headerSize;
	public Widget header;
	public Widget widget;

	public LayoutData(Widget widget, Widget header, double headerSize) {
	    this.widget = widget;
	    this.header = header;
	    this.headerSize = headerSize;
	}
    }

    private static final int ANIMATION_TIME = 250;

    private LayoutPanel layoutPanel;
    private final Unit unit;
    private final ArrayList<LayoutData> layoutData = new ArrayList<LayoutData>();
    private Widget visibleWidget;

    /**
     * Creates an empty stack panel.
     * 
     * @param unit
     *            the unit to be used for layout
     */
    public AccordionPanel() {
	this.unit = Unit.PX;
	initWidget(layoutPanel = new LayoutPanel());
	layoutPanel.setStyleName("hablar-AccordionPanel");
    }

    public void add(Widget w) {
	assert false : "Single-argument add() is not supported for this widget";
    }

    /**
     * Adds a child widget to this stack, along with a widget representing the
     * stack header.
     * 
     * @param widget
     *            the child widget to be added
     * @param header
     *            the header widget
     * @param headerSize
     *            the size of the header widget
     */
    public void add(final Widget widget, Widget header, double headerSize) {
	layoutPanel.add(header);
	layoutPanel.add(widget);

	layoutPanel.setWidgetLeftRight(header, 0, Unit.PX, 0, Unit.PX);
	layoutPanel.setWidgetLeftRight(widget, 0, Unit.PX, 0, Unit.PX);

	LayoutData data = new LayoutData(widget, header, headerSize);
	layoutData.add(data);

	if (visibleWidget == null) {
	    visibleWidget = widget;
	}

	GWT.log("Accordion widget add", null);

	if (isVisible()) {
	    animate(250);
	}
    }

    public void clear() {
	layoutPanel.clear();
	visibleWidget = null;
    }

    public Widget getVisibleWidget() {
	return visibleWidget;
    }

    /**
     * Check if the given widget is a child of this accordion
     * 
     * @param widget
     *            the widget to check
     * @return true if the widget is in the accordion, false if not
     */
    public boolean hasWidget(Widget widget) {
	return widget.getParent() == layoutPanel;
    }

    public Iterator<Widget> iterator() {
	return new Iterator<Widget>() {
	    int i = 0, last = -1;

	    public boolean hasNext() {
		return i < layoutData.size();
	    }

	    public Widget next() {
		if (!hasNext()) {
		    throw new NoSuchElementException();
		}
		return layoutData.get(last = i++).widget;
	    }

	    public void remove() {
		if (last < 0) {
		    throw new IllegalStateException();
		}

		AccordionPanel.this.remove(layoutData.get(last).widget);
		i = last;
		last = -1;
	    }
	};
    }

    public void onResize() {
	layoutPanel.onResize();
    }

    /**
     * Because the accordion panel never changes the visibility of its children,
     * you can not remove the visible widget
     * 
     * @see http://code.google.com/p/google-web-toolkit/issues/detail?id=4174
     */
    public boolean remove(Widget child) {
	if (!hasWidget(child)) {
	    return false;
	}

	int index = getLayoutDataIndex(child);
	LayoutData data = layoutData.get(index);
	layoutPanel.remove(data.header);
	layoutPanel.remove(child);
	layoutData.remove(index);
	if (layoutPanel.getWidgetCount() > 0)
	    animate(250);
	else {
	    visibleWidget = null;
	    layoutPanel.forceLayout();
	}

	return true;
    }

    /**
     * Shows the specified widget.
     * 
     * @param widget
     *            the child widget to be shown.
     */
    public void showWidget(Widget widget) {
	showWidget(widget, ANIMATION_TIME);
    }

    private void animate(int duration) {
	double top = 0, bottom = 0;
	int i = 0, visibleIndex = -1;
	for (; i < layoutData.size(); ++i) {
	    LayoutData data = layoutData.get(i);
	    layoutPanel.setWidgetTopHeight(data.header, top, unit, data.headerSize, unit);

	    top += data.headerSize;

	    layoutPanel.setWidgetTopHeight(data.widget, top, unit, 0, unit);

	    if (data.widget == visibleWidget) {
		visibleIndex = i;
		break;
	    }
	}

	assert visibleIndex != -1;

	for (int j = layoutData.size() - 1; j > i; --j) {
	    LayoutData data = layoutData.get(j);
	    layoutPanel.setWidgetBottomHeight(data.header, bottom, unit, data.headerSize, unit);
	    layoutPanel.setWidgetBottomHeight(data.widget, bottom, unit, 0, unit);
	    bottom += data.headerSize;
	}

	LayoutData data = layoutData.get(visibleIndex);
	layoutPanel.setWidgetTopBottom(data.widget, top, unit, bottom, unit);

	layoutPanel.animate(duration);
    }

    private int getLayoutDataIndex(Widget child) {
	int size = layoutData.size();
	for (int index = 0; index < size; index++) {
	    if (layoutData.get(index).widget == child)
		return index;
	}
	return -1;
    }

    private void showWidget(Widget widget, final int duration) {
	visibleWidget = widget;
	GWT.log("ACCORDION visible: " + visibleWidget, null);
	animate(duration);
    }

    @Override
    protected void onLoad() {
	if (layoutPanel.getWidgetCount() > 0)
	    animate(0);
    }
}
