package com.calclab.hablar.core.client.pages;

import static com.google.gwt.dom.client.Style.Unit.PCT;
import static com.google.gwt.dom.client.Style.Unit.PX;

import java.util.ArrayList;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.layout.client.Layout.AnimationCallback;
import com.google.gwt.layout.client.Layout.Layer;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class OverlayContainer implements PagesContainer {
    private static final String STYLE_OVERLAY = "hablar-Overlay";
    public static final String TYPE = "Overlay";
    private final LayoutPanel panel;
    private Page<?> currentPagePresenter;
    private final ArrayList<Page<?>> pages;

    public OverlayContainer(LayoutPanel parent) {
	this.panel = new LayoutPanel();
	this.pages = new ArrayList<Page<?>>();
	panel.setVisible(false);
	parent.add(panel);
	parent.setWidgetLeftRight(panel, 0, PX, 0, PX);
	parent.setWidgetTopBottom(panel, 0, PX, 0, PX);
	parent.forceLayout();
    }

    @Override
    public boolean add(Page<?> page) {
	pages.add(page);
	return true;
    }

    @Override
    public boolean focus(Page<?> page) {
	if (pages.contains(page)) {
	    assert currentPagePresenter == null : "Only one page in overlay";
	    this.currentPagePresenter = page;
	    Widget widget = currentPagePresenter.getDisplay().asWidget();
	    widget.addStyleName(STYLE_OVERLAY);
	    panel.add(widget);
	    panel.setWidgetLeftRight(widget, 0, PX, 0, PX);
	    panel.setWidgetTopHeight(widget, 0, PX, 0, PX);
	    panel.forceLayout();
	    panel.setVisible(true);
	    panel.setWidgetTopHeight(widget, 0, PX, 100, PCT);
	    panel.animate(500);
	    return true;
	}
	return false;
    }

    @Override
    public String getRol() {
	return TYPE;
    }

    @Override
    public Widget getWidget() {
	return panel;
    }

    @Override
    public boolean hide(Page<?> page) {
	if (currentPagePresenter == page) {
	    final Widget widget = currentPagePresenter.getDisplay().asWidget();
	    panel.setWidgetTopHeight(widget, 0, PX, 0, PX);
	    panel.animate(250, new AnimationCallback() {
		@Override
		public void onAnimationComplete() {
		    widget.removeStyleName(STYLE_OVERLAY);
		    panel.remove(widget);
		    currentPagePresenter = null;
		    panel.setVisible(false);
		}

		@Override
		public void onLayout(Layer layer, double progress) {
		}
	    });
	    return true;
	}
	return false;
    }

    @Override
    public boolean remove(Page<?> page) {
	return pages.remove(page);
    }

}
