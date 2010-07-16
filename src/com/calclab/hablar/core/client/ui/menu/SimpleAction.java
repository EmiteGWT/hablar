package com.calclab.hablar.core.client.ui.menu;


public abstract class SimpleAction<T> implements Action<T> {
    private final String name;
    private final String id;
    private final String icon;

    public SimpleAction(final String name, final String id) {
	this(name, id, null);
    }

    public SimpleAction(final String name, final String id, final String icon) {
	this.name = name;
	this.id = id;
	this.icon = icon;
    }

    @Override
    public abstract void execute(T target);

    @Override
    public String getIcon() {
	return icon;
    }

    @Override
    public String getId() {
	return id;
    }

    @Override
    public String getName() {
	return name;
    }

    public boolean isApplicable(final T target) {
	return true;
    }
}
