package com.calclab.hablar.roster.client.ui.move;

import java.util.List;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class MoveToGroupPresenter extends PagePresenter<MoveToGroupDisplay> {

    private static final String TYPE = "MoveToGroup";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static int id = 0;
    private RosterItem item;

    public MoveToGroupPresenter(final HablarEventBus eventBus, final MoveToGroupDisplay display) {
	super(TYPE, "" + ++id, eventBus, display);

	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getApply().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final String groupName = display.getNewGroupName().getText().trim();
		moveToNewGroup(groupName);
		requestVisibility(Visibility.hidden);
	    }
	});
    }

    protected void moveToNewGroup(final String groupName) {
	final Roster roster = Suco.get(Roster.class);
	final List<String> groups = item.getGroups();
	groups.add(groupName);
	final String[] newList = groups.toArray(EMPTY_STRING_ARRAY);
	roster.updateItem(item.getJID(), item.getName(), newList);
    }

    public void setItem(final RosterItem item) {
	this.item = item;

    }

}
