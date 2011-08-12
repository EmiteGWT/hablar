package com.calclab.hablar.core.client.ui.menu;

import com.google.gwt.resources.client.ImageResource;

public interface Action<T> {

	void execute(T target);

	ImageResource getIcon();

	String getId();

	String getDescription();

	boolean isApplicable(T target);

}
