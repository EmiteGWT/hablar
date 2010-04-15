package com.calclab.hablar.core.client.pages.tabs;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.HablarDisplay;
import com.calclab.hablar.core.client.container.main.MainLayout;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.google.gwt.user.client.ui.Widget;

public class TabsLayout extends MainLayout {

    public static class TabHeaderSize {
	public static TabHeaderSize DEFAULT_SIZE = new TabHeaderSize("24px", "120px", 10);

	private String height;

	private String width;

	private int trim;

	public static TabHeaderSize create(String height, String width, Integer trim) {
	    TabHeaderSize retValue = DEFAULT_SIZE.createCopy();
	    if (height != null) {
		retValue.height = height;
	    }
	    if (width != null) {
		retValue.width = width;
	    }
	    if (trim != null) {
		retValue.trim = trim;
	    }
	    return retValue;
	}

	public TabHeaderSize(String height, String width, int trim) {
	    this.height = height;
	    this.width = width;
	    this.trim = trim;
	}

	public String getHeight() {
	    return height;
	}

	public String getWidth() {
	    return width;
	}

	public int getTrim() {
	    return trim;
	}

	protected TabHeaderSize createCopy()  {
	    return new TabHeaderSize(height, width, trim);
	}
    }

    private static final double BAR_SIZE = 24;
    private static TabsPanel tabs;

    private final TabsMenuPresenter tabsMenuPresenter;

    private TabHeaderSize tabHeaderSize;

    public TabsLayout(final HablarDisplay parent) {
	this(parent, TabHeaderSize.DEFAULT_SIZE);
    }

    public TabsLayout(final HablarDisplay parent, TabHeaderSize tabHeaderSize) {
	super(tabs = new TabsPanel(BAR_SIZE, PX), parent);
	tabsMenuPresenter = new TabsMenuPresenter(tabs.getMenu(), this);
	this.tabHeaderSize = tabHeaderSize;
    }

    @Override
    public void add(final Widget pageWidget, final Widget headWidget) {
	tabs.add(pageWidget, headWidget);
    }

    @Override
    public HeaderDisplay createHeaderDisplay(final Page<?> page) {
	return new TabsHeaderWidget(page.getId(), tabHeaderSize.getHeight(), tabHeaderSize.getWidth(), tabHeaderSize
		.getTrim());
    }

    @Override
    public void focus(final Widget pageWidget) {
	tabs.selectTab(pageWidget);
    }

    public TabsMenuPresenter getTabsMenuPresenter() {
	return tabsMenuPresenter;
    }

    @Override
    public void remove(final Widget pageWidget) {
	tabs.remove(pageWidget);
    }

}
