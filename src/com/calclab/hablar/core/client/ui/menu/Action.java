package com.calclab.hablar.core.client.ui.menu;

public abstract class Action<T> {
    private final String name;
    private final String id;
    private final String iconStyle;

    public Action(final String name, final String id, String iconStyle) {
	this.name = name;
	this.id = id;
	this.iconStyle = iconStyle;
    }

    public Action(String name, String id) {
	this(name, id, null);
    }

    public abstract void execute(T target);

    public String getName() {
	return name;
    }

    public String getId() {
	return id;
    }

    public String getIconStyle() {
	return iconStyle;
    }
}
