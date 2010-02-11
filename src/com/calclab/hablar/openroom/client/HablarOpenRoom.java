package com.calclab.hablar.openroom.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.rooms.client.HablarRoomsConfig;
import com.calclab.hablar.roster.client.RosterPage;
import com.google.gwt.core.client.EntryPoint;

public class HablarOpenRoom implements EntryPoint {
    private static final String ACTION_ID = "HablarOpenRoom-openRoomAction";

    protected static Action<RosterPage> createAction(final OpenRoomPage page) {
	final String name = "Open new room";
	final String icon = HablarIcons.get(IconType.buddyWait);
	final Action<RosterPage> action = new Action<RosterPage>(name, ACTION_ID, icon) {
	    @Override
	    public void execute(final RosterPage target) {
		page.requestVisibility(Visibility.focused);
	    }
	};
	return action;
    }

    public static void install(final Hablar hablar) {
	install(hablar, HablarRoomsConfig.getFromMeta());
    }

    public static void install(final Hablar hablar, final HablarRoomsConfig roomsConfig) {
	final OpenRoomDisplay display = new OpenRoomWidget();
	final OpenRoomPage page = new OpenRoomPage(hablar.getEventBus(), roomsConfig.roomService, display);
	hablar.addPage(page, OverlayContainer.ROL);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage roster = RosterPage.asRoster(event.getPage());
		if (roster != null) {
		    roster.addAction(createAction(page));
		}
	    }
	}, true);
    }

    @Override
    public void onModuleLoad() {
    }

}
