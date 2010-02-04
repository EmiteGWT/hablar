package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.user.client.ui.LayoutPanel;

public interface HablarDisplay extends Display {
    public static enum Layout {
	accordion, tabs
    }

    LayoutPanel getContainer();
}
