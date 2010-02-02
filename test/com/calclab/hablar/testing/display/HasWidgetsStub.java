package com.calclab.hablar.testing.display;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class HasWidgetsStub implements HasWidgets {
    private final ArrayList<Widget> widgets;
    private Widget lastAdded;
    private boolean hasBeenCleared;
    private Widget lastRemoved;

    public HasWidgetsStub() {
	this.widgets = new ArrayList<Widget>();
	hasBeenCleared = false;
    }

    @Override
    public void add(Widget widget) {
	lastAdded = widget;
	widgets.add(widget);
    }

    @Override
    public void clear() {
	hasBeenCleared = true;
	widgets.clear();
    }

    public Widget getLastAdded() {
	return lastAdded;
    }

    public Widget getLastRemoved() {
	return lastRemoved;
    }

    public boolean hasBeenCleared() {
	return hasBeenCleared;
    }

    @Override
    public Iterator<Widget> iterator() {
	return widgets.iterator();
    }

    @Override
    public boolean remove(Widget widget) {
	lastRemoved = widget;
	return widgets.remove(widget);
    }

}
