package com.calclab.hablar.dock.client;

import com.calclab.emite.browser.client.PageAssist;

public class DockConfig {
    public static DockConfig getFromMeta() {
	final DockConfig dockConfig = new DockConfig();
	final String rosterDock = PageAssist.getMeta("hablar.dock.roster");
	dockConfig.rosterDock = "right".equalsIgnoreCase(rosterDock) ? "right" : "left";
	return dockConfig;
    }

    public String rosterDock = "left";
    public double rosterWidth = 250;
    public int headerSize = 24;

}
