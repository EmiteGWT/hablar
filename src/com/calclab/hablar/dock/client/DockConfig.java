package com.calclab.hablar.dock.client;

import static com.google.gwt.dom.client.Style.Unit.PX;

import java.util.HashMap;

import com.google.gwt.dom.client.Style.Unit;

/**
 * Configures the Dock container
 */
public class DockConfig {

    public static class Dock {
	final String pageType;
	final int size;
	final Unit unit;

	public Dock(String pageType, int size, Unit unit) {
	    this.pageType = pageType;
	    this.size = size;
	    this.unit = unit;
	}
    }

    public static enum Position {
	top, left, right, bottom
    }

    HashMap<Position, Dock> docks = new HashMap<Position, Dock>();

    public Dock get(Position position) {
	Dock dock = docks.get(position);
	if (dock == null) {
	    dock = new Dock(null, 0, PX);
	    docks.put(position, dock);
	}
	return dock;
    }

    public void set(Position position, String type, int size) {
	docks.put(position, new Dock(type, size, PX));
    }
}
