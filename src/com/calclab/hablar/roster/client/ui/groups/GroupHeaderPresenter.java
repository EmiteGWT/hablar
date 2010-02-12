package com.calclab.hablar.roster.client.ui.groups;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class GroupHeaderPresenter implements Presenter<GroupHeaderDisplay> {

    private final GroupHeaderDisplay display;

    public GroupHeaderPresenter(final RosterGroupPresenter group, final Menu<RosterGroupPresenter> groupMenu,
	    final GroupHeaderDisplay display) {
	this.display = display;
	display.getName().setText(group.getGroupLabel());
	display.setMenuVisible(!group.isAllContacts());

	display.getToggleVisibility().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		group.toggleVisibility();
	    }
	});
	display.getOpenMenu().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		event.preventDefault();
		final Element element = event.getRelativeElement();
		final int width = element.getClientWidth();
		groupMenu.setTarget(group);
		groupMenu.show(element.getAbsoluteLeft() - width, element.getAbsoluteTop());
	    }
	});
    }

    @Override
    public GroupHeaderDisplay getDisplay() {
	return display;
    }

}
