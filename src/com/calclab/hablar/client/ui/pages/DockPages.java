package com.calclab.hablar.client.ui.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DockPages extends PagesWidget {

    interface DockPagesUiBinder extends UiBinder<Widget, DockPages> {
    }

    private static DockPagesUiBinder uiBinder = GWT.create(DockPagesUiBinder.class);

    @UiField
    TabPages pages;

    @UiField
    DockLayoutPanel west;

    private PageWidget docked;

    public DockPages() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void add(PageWidget page, Position position) {
	if (position == Position.WEST) {
	    this.docked = page;
	    west.addNorth(page.getHeader(), 22);
	    west.add(page);
	} else {
	    pages.add(page, position);
	}
    }

    @Override
    public void remove(PageWidget page) {
	if (page != docked) {
	    pages.remove(page);
	} else {
	    west.remove(docked);
	}
    }

    @Override
    protected void addPage(PageWidget page) {
    }

    @Override
    protected void showPage(PageWidget page) {
	if (page != docked) {
	    pages.showPage(page);
	}
    }

}
