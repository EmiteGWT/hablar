package com.calclab.hablar.roster.client.groups;

import static com.calclab.hablar.roster.client.HablarRoster.i18n;

import com.calclab.emite.im.client.roster.RosterGroup;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class GroupHeaderPresenter implements Presenter<GroupHeaderDisplay> {

    private final GroupHeaderDisplay display;

    public GroupHeaderPresenter(final RosterGroupPresenter groupPresenter, final Menu<RosterGroupPresenter> groupMenu,
	    final GroupHeaderDisplay display) {
	this.display = display;
	final RosterGroup group = groupPresenter.getRosterGroup();
	final boolean isAllContacts = group.getName() == null;
	display.setMenuVisible(!isAllContacts);

	display.getToggleVisibility().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		groupPresenter.toggleVisibility();
		final boolean visible = groupPresenter.isVisible();
		setVisibleStyle(visible);
	    }
	});
	display.getOpenMenu().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		event.preventDefault();
		final Element element = event.getRelativeElement();
		groupMenu.setTarget(groupPresenter);
		groupMenu.show(element.getAbsoluteLeft(), element.getAbsoluteTop());
	    }
	});

	final Listener<RosterItem> updateGroup = new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem parameter) {
		final String groupLabel = isAllContacts ? i18n().allContactsGroupName() : i18n().groupName(group.getName(),
			"" + group.getSize());
		display.getName().setText(groupLabel);
	    }
	};
	group.onItemAdded(updateGroup);
	group.onItemRemoved(updateGroup);
	setVisibleStyle(groupPresenter.isVisible());
    }

    @Override
    public GroupHeaderDisplay getDisplay() {
	return display;
    }

    protected void setVisibleStyle(final boolean visible) {
	if (visible) {
	    display.removeStyleName("collapsed");
	} else {
	    display.addStyleName("collapsed");
	}
    }

}
