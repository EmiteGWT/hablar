package com.calclab.hablar.client.ui.pages;

import com.calclab.hablar.client.ui.pages.Page.Visibility;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DockPages extends AbstractPages {

    interface DockPagesUiBinder extends UiBinder<Widget, DockPages> {
    }

    private static DockPagesUiBinder uiBinder = GWT.create(DockPagesUiBinder.class);

    @UiField
    TabPages pages;

    @UiField
    DockLayoutPanel west;

    private Page docked;

    public DockPages() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void add(Page page, Visibility visibility) {
	// FIXME: take care with this
	if (page.getHeader().getTitle().equals("Roster")) {
	    this.docked = page;
	    west.addNorth(page.getHeader(), 22);
	    west.add((Widget) page);
	} else {
	    pages.add(page, visibility);
	}
    }

    @Override
    public boolean hasPage(Page page) {
	if (page == docked) {
	    return true;
	} else {
	    return pages.hasPage(page);
	}
    }

    @Override
    public void removePage(Page page) {
	if (page != docked) {
	    pages.hide(page);
	}
    }

    @Override
    protected void addPage(Page page) {
	assert false : "Do not call this";
    }

    @Override
    protected void showPage(Page page) {
	if (page != docked) {
	    pages.showPage(page);
	}
    }

}
