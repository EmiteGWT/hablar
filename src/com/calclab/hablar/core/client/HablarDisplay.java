package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.AnimatedLayout;
import com.google.gwt.user.client.ui.Widget;

public interface HablarDisplay extends Display, AnimatedLayout {
    public static enum Layout {
	accordion, tabs
    }

    public void setWidgetBottomHeight(Widget child, double bottom, Unit bottomUnit, double height, Unit heightUnit);

    public void setWidgetLeftRight(Widget child, double left, Unit leftUnit, double right, Unit rightUnit);

    public void setWidgetLeftWidth(Widget child, double left, Unit leftUnit, double width, Unit widthUnit);

    public void setWidgetRightWidth(Widget child, double right, Unit rightUnit, double width, Unit widthUnit);

    public void setWidgetTopBottom(Widget child, double top, Unit topUnit, double bottom, Unit bottomUnit);

    public void setWidgetTopHeight(Widget child, double top, Unit topUnit, double height, Unit heightUnit);

    void add(Widget widget);
}
