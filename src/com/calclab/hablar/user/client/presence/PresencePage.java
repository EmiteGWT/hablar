package com.calclab.hablar.user.client.presence;

import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.GWT;
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
    public static final String CLEAR_CUSTOM = "PresencePage-ClearCustom";
    private static int id = 0;
    private final PresenceManager manager;

    public PresencePage(final HablarEventBus eventBus, final PresenceDisplay display,
	    final Menu<PresencePage> statusMenu) {
	super(TYPE, "" + (++id), eventBus, display);
	manager = Suco.get(PresenceManager.class);
	final String style = HablarIcons.get(IconType.buddy);
	model.init(style, "Your status");
	addActions(statusMenu);
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

    @Override
    public void saveData() {
	GWT.log("CHANGE PRESENCE!", null);
	final Presence presence = manager.getOwnPresence();
	presence.setStatus(display.getStatusText().getText());
	manager.changeOwnPresence(presence);
	// TODO: Save custom presences
    }

    @Override
    public void showData() {
	GWT.log("LOAD PRESENCE!", null);
	final Presence presence = manager.getOwnPresence();
	display.getStatusText().setText(presence.getStatus());
	setShowIcon(presence.getShow());
	// TODO: Restore custom presences
    }

    protected void setPresence(final String status, final Show show) {
	setShowIcon(show);
	display.getStatusText().setText(status);
	final Presence presence = manager.getOwnPresence();
	presence.setStatus(status);
	presence.setShow(show);
	manager.changeOwnPresence(presence);
    }

    private void addActions(final Menu<PresencePage> statusMenu) {
	statusMenu.addAction(new SimpleAction<PresencePage>("Available", ACTION_ID_AVAILABLE, HablarIcons
		.get(IconType.buddyOn)) {
	    @Override
	    public void execute(final PresencePage target) {
		setPresence("", Show.notSpecified);
	    }
	});
	statusMenu.addAction(new SimpleAction<PresencePage>("Available with Custom Message...",
		ACTION_ID_AVAILABLE_CUSTOM, HablarIcons.get(IconType.buddyOn)) {
	    @Override
	    public void execute(final PresencePage target) {
		setPresence("", Show.notSpecified);
		display.focusInStatus();
	    }
	});
	statusMenu
		.addAction(new SimpleAction<PresencePage>("Busy", ACTION_ID_BUSY, HablarIcons.get(IconType.buddyOff)) {
		    @Override
		    public void execute(final PresencePage target) {
			setPresence("", Show.dnd);
		    }
		});
	statusMenu.addAction(new SimpleAction<PresencePage>("Busy with Custom Message...", ACTION_ID_BUSY_CUSTOM,
		HablarIcons.get(IconType.buddyOff)) {
	    @Override
	    public void execute(final PresencePage target) {
		setPresence("", Show.dnd);
		display.focusInStatus();
	    }
	});
    }

    private void setShowIcon(final Show show) {
	display.setStatusIcon(PresenceIcon.getIcon(true, show));
    }

}
