package com.calclab.hablar.user.client.presence;

import static com.calclab.hablar.user.client.HablarUser.i18n;

import java.util.ArrayList;
import java.util.List;

import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIconsBundle;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.hablar.user.client.storedpresence.StoredPresence;
import com.calclab.hablar.user.client.storedpresence.StoredPresenceManager;
import com.calclab.hablar.user.client.storedpresence.StoredPresences;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.resources.client.ImageResource;

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
    private final ArrayList<SimpleAction<PresencePage>> defaultActions;
    private SimpleAction<PresencePage> clearCustomsAction;

    public PresencePage(final HablarEventBus eventBus, final PresenceDisplay display) {
	super(TYPE, "" + ++id, eventBus, display);

	defaultActions = new ArrayList<SimpleAction<PresencePage>>();
	statusMenu = new Menu<PresencePage>(display.newStatusMenuDisplay("hablar-StatusItemMenu"));

	manager = Suco.get(PresenceManager.class);
	String title = i18n().presencePageTitle();
	model.init(HablarIcons.getBundle().buddyIcon(), title, title);
	display.setStatusIcon(HablarIcons.getBundle().buddyIconOff());
	display.setPageTitle(i18n().presencePageTitle());
	storedPresenceManager = new StoredPresenceManager(Suco.get(PrivateStorageManager.class));
	createDefActions();
	updateMenu();

	final ClickHandler handler = new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		event.preventDefault();
		final Element element = event.getRelativeElement();
		statusMenu.setTarget(PresencePage.this);
		statusMenu.show(element.getAbsoluteLeft(), element.getAbsoluteTop());
	    }
	};
	display.getMenu().addClickHandler(handler);
	display.getIcon().addClickHandler(handler);

	display.getStatus().addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(final KeyDownEvent event) {
		if (event.getNativeKeyCode() == 13) {
		    updateStatus(display);
		}
	    }

	});
	display.getStatusFocus().addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(final BlurEvent event) {
		updateStatus(display);
	    }
	});
	manager.onOwnPresenceChanged(new Listener<Presence>() {
	    @Override
	    public void onEvent(final Presence presence) {
		showPresence(presence.getStatus(), presence.getShow());
	    }
	});
    }

    public SimpleAction<PresencePage> createCustomAction(final String title, final String id, final ImageResource icon,
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
			    final Show show = presence.getShow();
			    statusMenu.addAction(createCustomAction(presence.getStatus(), null, PresenceIcon
				    .getIconResource(true, show), presence.getStatus(), show));
			}
			statusMenu.addAction(clearCustomsAction);
		    }
		}
	    }
	});
    }

    private SimpleAction<PresencePage> createAction(final String label, final String actionId, final ImageResource iconType,
	    final String status, final Show show) {
	final boolean hasStatus = status != null;
	return new SimpleAction<PresencePage>(label, actionId, iconType) {
	    @Override
	    public void execute(final PresencePage target) {
		setPresence(status, show);
		display.setStatusEnabled(hasStatus);
		display.setStatusFocused(hasStatus);
	    }
	};
    }

    private void createDefActions() {
	HablarIconsBundle bundle = HablarIcons.getBundle();
	defaultActions.add(createAction(i18n().available(), ACTION_ID_AVAILABLE, bundle.buddyIconOn(), null, Show.chat));
	defaultActions.add(createAction(i18n().availableCustom(), ACTION_ID_AVAILABLE_CUSTOM, bundle.buddyIconOn(), "", Show.chat));
	defaultActions.add(createAction(i18n().busy(), ACTION_ID_BUSY, bundle.buddyIconDnd(), null, Show.dnd));
	defaultActions.add(createAction(i18n().busyCustom(), ACTION_ID_BUSY_CUSTOM, bundle.buddyIconDnd(), "", Show.dnd));
	clearCustomsAction = new SimpleAction<PresencePage>(i18n().clearCustom(), ACTION_CLEAR_CUSTOM, bundle.closeIcon()) {
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
	display.setStatusIcon(PresenceIcon.getIconResource(true, show));
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
	for (final SimpleAction<PresencePage> action : defaultActions) {
	    statusMenu.addAction(action);
	}
	addCustomPresenceActions();
    }

    private void updateStatus(final PresenceDisplay display) {
	setPresence(display.getStatusText().getText(), manager.getOwnPresence().getShow());
    }

}
