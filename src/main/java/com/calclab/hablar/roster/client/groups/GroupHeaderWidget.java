package com.calclab.hablar.roster.client.groups;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class GroupHeaderWidget extends Composite implements GroupHeaderDisplay {

	interface GroupHeaderWidgetUiBinder extends UiBinder<Widget, GroupHeaderWidget> {
	}

	private static GroupHeaderWidgetUiBinder uiBinder = GWT.create(GroupHeaderWidgetUiBinder.class);

	@UiField
	Label name;

	@UiField
	Image menu;

	private static final String COLLAPSED_CLASS = "hablar-lightColor";

	public GroupHeaderWidget(final String id) {
		initWidget(uiBinder.createAndBindUi(this));
		name.ensureDebugId("GroupHeaderWidget-name-" + id);
		menu.ensureDebugId("GroupHeaderWidget-menu-" + id);
	}

	@Override
	public HasText getName() {
		return name;
	}

	@Override
	public HasClickHandlers getOpenMenu() {
		return menu;
	}

	@Override
	public HasClickHandlers getToggleVisibility() {
		return name;
	}

	@Override
	public void setCollapsed(final boolean isCollapsed) {
		if (isCollapsed) {
			addStyleName(COLLAPSED_CLASS);
			// Icons.set(toggle, Icons.PLUS);
		} else {
			removeStyleName(COLLAPSED_CLASS);
			// Icons.set(toggle, Icons.LESS);
		}
	}

	@Override
	public void setMenuVisible(final boolean visible) {
		menu.setVisible(visible);
	}

}
