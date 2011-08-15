package com.calclab.hablar.core.client.ui.menu;

import com.google.gwt.resources.client.ImageResource;

public abstract class SimpleAction<T> implements Action<T> {
	private final String description;
	private final String id;
	private final ImageResource icon;

	public SimpleAction(final String name, final String id) {
		this(name, id, null);
	}

	public SimpleAction(final String description, final String id, final ImageResource icon) {
		this.description = description;
		this.id = id;
		this.icon = icon;
	}

	@Override
	public abstract void execute(T target);

	@Override
	public ImageResource getIcon() {
		return icon;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isApplicable(final T target) {
		return true;
	}
}
