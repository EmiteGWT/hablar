package com.calclab.hablar.core.client.container.overlay;

import static com.google.gwt.dom.client.Style.Unit.PCT;
import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.HablarDisplay;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.layout.client.Layout.AnimationCallback;
import com.google.gwt.layout.client.Layout.Layer;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class OverlayLayout {
    private final LayoutPanel panel;
    private static final String STYLE_OVERLAY = "hablar-Overlay";

    public OverlayLayout(HablarDisplay display) {
	this.panel = new LayoutPanel();
	panel.addStyleName("hablar-OverlayContainer");
	display.add(panel);
	display.setWidgetLeftRight(panel, 0, PX, 0, PX);
	display.setWidgetTopBottom(panel, 0, PX, 0, PX);
	
	panel.getElement().getParentElement().addClassName("hablar-OverlayContainerOuter");
    }

    public void add(Widget widget) {
	widget.addStyleName(STYLE_OVERLAY);
	panel.add(widget);
	// FIXME: Animation
	panel.setWidgetLeftRight(widget, 0, PX, 0, PX);
	panel.setWidgetTopHeight(widget, 0, PX, 0, PX);
	//panel.forceLayout();
	setVisible(true);
	panel.setWidgetTopBottom(widget, 0, PX, 0, PX);
	panel.getWidgetContainerElement(widget).getStyle().setOverflow(Overflow.VISIBLE);
	panel.animate(500);
    }

    public Widget getWidget() {
	return panel;
    }

    public void remove(final Widget widget) {
	// FIXME: Animation
	panel.setWidgetTopHeight(widget, 0, PX, 100, PCT);
	//panel.forceLayout();
	panel.setWidgetTopHeight(widget, 0, PX, 0, PX);
	panel.animate(250, new AnimationCallback() {
	    @Override
	    public void onAnimationComplete() {
		widget.removeStyleName(STYLE_OVERLAY);
		panel.remove(widget);
		setVisible(false);
	    }

	    @Override
	    public void onLayout(Layer layer, double progress) {
	    }
	});

    }

    public void setVisible(boolean visible) {
	panel.setVisible(visible);
	
	// +++ Serious bodge for IE6
	// Sets the z-index to 1 if visible, -1 if not 
	if(panel.getElement().getParentElement() != null) {
	    panel.getElement().getParentElement().getStyle().setZIndex(visible ? 1 : -1);
	}
	// ---
    }

}
