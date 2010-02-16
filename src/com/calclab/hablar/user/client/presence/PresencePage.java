package com.calclab.hablar.user.client.presence;

import java.util.List;

import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.hablar.user.client.storedpresence.StoredPresence;
import com.calclab.hablar.user.client.storedpresence.StoredPresenceManager;
import com.calclab.hablar.user.client.storedpresence.StoredPresences;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class PresencePage extends PagePresenter<PresenceDisplay> implements EditorPage<PresenceDisplay> {
    public static final String TYPE = "Presence";
    public static final String ACTION_ID_AVAILABLE = "PresencePage-AvailableStatus";
    public static final String ACTION_ID_AVAILABLE_CUSTOM = "PresencePage-AvailableStatus-Custom";
    public static final String ACTION_ID_BUSY = "PresencePage-BusyStatus";
    public static final String ACTION_ID_BUSY_CUSTOM = "PresencePage-BusyStatus-Custom";
    public static final String ACTION_CLEAR_CUSTOM = "PresencePage-ClearCustom";
    private static int id = 0;
    private final PresenceManager manager;
    private final StoredPresenceManager storedPresenceManager;
    private final Menu<PresencePage> statusMenu;
    private SimpleAction<PresencePage> availableAction;
    private SimpleAction<PresencePage> availableCustomAction;
    private SimpleAction<PresencePage> busyAction;
    private SimpleAction<PresencePage> busyCustomAction;
    private SimpleAction<PresencePage> clearCustomsAction;

    public PresencePage(final HablarEventBus eventBus, final PresenceDisplay display,
	    final Menu<PresencePage> statusMenu) {
	super(TYPE, "" + (++id), eventBus, display);
	this.statusMenu = statusMenu;
	manager = Suco.get(PresenceManager.class);
	final String style = HablarIcons.get(IconType.buddy);
	model.init(style, "Your status");
	storedPresenceManager = new StoredPresenceManager(Suco.get(PrivateStorageManager.class));
	createDefActions();
	updateMenu();
	display.getMenuAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		event.preventDefault();
		final Element element = event.getRelativeElement();
		final int width = element.getClientWidth();
		statusMenu.setTarget(PresencePage.this);
		statusMenu.show(element.getAbsoluteLeft() - width, element.getAbsoluteTop());
	    }
	});
	display.getStatus().addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(final KeyDownEvent event) {
		if (event.getNativeKeyCode() == 13) {
		    saveData();
		}
	    }
	});

    }

    public SimpleAction<PresencePage> createCustomAction(final String title, final String id, final String icon,
	    final String status, final Show show) {
	return new SimpleAction<PresencePage>(title, id, icon) {
	    @Override
	    public void execute(final PresencePage target) {
		setPresence(status, show);
	    }
	};
    }

    @Override
    public void saveData() {
	setPresence(display.getStatusText().getText(), manager.getOwnPresence().getShow());
    }

    @Override
    public void showData() {
	final Presence presence = manager.getOwnPresence();
	showPresence(presence.getStatus(), presence.getShow());
    }

    private void addCustomPresenceActions() {
	storedPresenceManager.get(new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse response) {
		if (response.isSuccess()) {
		    final List<StoredPresence> presences = StoredPresences.parse(response).get();
		    if (presences.size() > 0) {
			for (final StoredPresence presence : presences) {
			    statusMenu.addAction(createCustomAction(presence.getStatus(), null, HablarIcons
				    .get(IconType.buddyOn), presence.getStatus(), presence.getShow()));
			}
			statusMenu.addAction(clearCustomsAction);
		    }
		}
	    }
	});
    }

    private void addDefActions() {
	statusMenu.addAction(availableAction);
	statusMenu.addAction(availableCustomAction);
	statusMenu.addAction(busyAction);
	statusMenu.addAction(busyCustomAction);
    }

    private void createDefActions() {
	availableAction = new SimpleAction<PresencePage>("Available", ACTION_ID_AVAILABLE, HablarIcons
		.get(IconType.buddyOn)) {
	    @Override
	    public void execute(final PresencePage target) {
		setPresence("", Show.notSpecified);
	    }
	};
	availableCustomAction = new SimpleAction<PresencePage>("Available with Custom Message...",
		ACTION_ID_AVAILABLE_CUSTOM, HablarIcons.get(IconType.buddyOn)) {
	    @Override
	    public void execute(final PresencePage target) {
		setPresence("", Show.notSpecified);
		display.focusInStatus();
	    }
	};
	busyAction = new SimpleAction<PresencePage>("Busy", ACTION_ID_BUSY, HablarIcons.get(IconType.buddyOff)) {
	    @Override
	    public void execute(final PresencePage target) {
		setPresence("", Show.dnd);
	    }
	};
	busyCustomAction = new SimpleAction<PresencePage>("Busy with Custom Message...", ACTION_ID_BUSY_CUSTOM,
		HablarIcons.get(IconType.buddyOff)) {
	    @Override
	    public void execute(final PresencePage target) {
		setPresence("", Show.dnd);
		display.focusInStatus();
	    }
	};
	clearCustomsAction = new SimpleAction<PresencePage>("Clear custom messages", ACTION_CLEAR_CUSTOM, HablarIcons
		.get(IconType.close)) {
	    @Override
	    public void execute(final PresencePage target) {
		storedPresenceManager.clearAll();
		updateMenu();
	    }
	};
    }

    private void setPresence(final String status, final Show show) {
	showPresence(status, show);
	if (statusNotEmpty(status)) {
	    storedPresenceManager.add(status, show, new Listener<IQResponse>() {
		@Override
		public void onEvent(final IQResponse response) {
		    if (response.isSuccess()) {
			updateMenu();
		    }
		}
	    });
	}
	final Presence presence = manager.getOwnPresence();
	presence.setStatus(status);
	presence.setShow(show);
	manager.changeOwnPresence(presence);
    }

    private void setShowIcon(final Show show) {
	display.setStatusIcon(PresenceIcon.getIcon(true, show));
    }

    private void showPresence(final String status, final Show show) {
	setShowIcon(show);
	display.getStatusText().setText(status);
    }

    private boolean statusNotEmpty(final String status) {
	return status != null && !status.isEmpty();
    }

    private void updateMenu() {
	statusMenu.clear();
	addDefActions();
	addCustomPresenceActions();
    }

}
