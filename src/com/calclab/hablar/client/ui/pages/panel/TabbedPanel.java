package com.calclab.hablar.client.ui.pages.panel;

import java.util.Iterator;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

/**
 * Our own tabbed panel
 */
public class TabbedPanel extends Composite implements HasWidgets, RequiresResize, ProvidesResize {

    private final Unit unit;
    private LayoutPanel layoutPanel;

    public TabbedPanel() {
	this.unit = Unit.PX;
	initWidget(layoutPanel = new LayoutPanel());
    }

    @Override
    public void add(Widget w) {
	// TODO Auto-generated method stub

    }

    @Override
    public void clear() {
	// TODO Auto-generated method stub

    }

    @Override
    public Iterator<Widget> iterator() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void onResize() {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean remove(Widget w) {
	// TODO Auto-generated method stub
	return false;
    }

}
