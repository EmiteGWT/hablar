package com.calclab.hablar.core.client.ui.actions;

import com.calclab.hablar.core.client.ui.menu.Action;
import com.google.gwt.user.client.ui.Label;

public class ActionWidget extends Label {
    public ActionWidget(final Action<?> action) {
	addStyleName("hablar-ActionWidget");
	addStyleName(action.getIconStyle());
	this.ensureDebugId(action.getId());
	setTitle(action.getName());
    }
}
