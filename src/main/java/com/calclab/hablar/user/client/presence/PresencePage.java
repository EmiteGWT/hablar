package com.calclab.hablar.user.client.presence;

import java.util.ArrayList;
import java.util.List;

import com.calclab.emite.core.client.xmpp.stanzas.IQ;
import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.im.client.presence.events.OwnPresenceChangedEvent;
import com.calclab.emite.im.client.presence.events.OwnPresenceChangedHandler;
import com.calclab.emite.xep.storage.client.events.PrivateStorageResponseEvent;
import com.calclab.emite.xep.storage.client.events.PrivateStorageResponseHandler;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.icons.client.IconsBundle;
import com.calclab.hablar.icons.client.PresenceIcon;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.hablar.user.client.UserMessages;
import com.calclab.hablar.user.client.storedpresence.StoredPresence;
import com.calclab.hablar.user.client.storedpresence.StoredPresenceManager;
import com.calclab.hablar.user.client.storedpresence.StoredPresences;
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
	private final PresenceManager presenceManager;
	private final StoredPresenceManager storedPresenceManager;
	private final Menu<PresencePage> statusMenu;
	private final ArrayList<SimpleAction<PresencePage>> defaultActions;
	private SimpleAction<PresencePage> clearCustomsAction;

	public PresencePage(final PresenceManager presenceManager, final StoredPresenceManager storageManager, final HablarEventBus eventBus,
			final PresenceDisplay display) {
		super(TYPE, "" + ++id, eventBus, display);
		this.presenceManager = presenceManager;
		storedPresenceManager = storageManager;

		defaultActions = new ArrayList<SimpleAction<PresencePage>>();
		statusMenu = new Menu<PresencePage>(display.newStatusMenuDisplay("hablar-StatusItemMenu"));

		final String title = UserMessages.msg.presencePageTitle();
		model.init(IconsBundle.bundle.buddyIcon(), title, title);
		display.setStatusIcon(IconsBundle.bundle.buddyIconOff());
		display.setPageTitle(UserMessages.msg.presencePageTitle());
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

		presenceManager.addOwnPresenceChangedHandler(new OwnPresenceChangedHandler() {
			@Override
			public void onOwnPresenceChanged(final OwnPresenceChangedEvent event) {
				final Presence presence = event.getCurrentPresence();
				showPresence(presence.getStatus(), presence.getShow());
			}
		});
	}

	private void addCustomPresenceActions() {
		storedPresenceManager.get(new PrivateStorageResponseHandler() {
			@Override
			public void onStorageResponse(PrivateStorageResponseEvent event) {
				IQ response = event.getResponseIQ();
				if (IQ.isSuccess(response)) {
					final List<StoredPresence> presences = StoredPresences.parse(response).get();
					if (presences.size() > 0) {
						for (final StoredPresence presence : presences) {
							final Show show = presence.getShow();
							statusMenu.addAction(createCustomAction(presence.getStatus(), null, PresenceIcon.get(true, show), presence.getStatus(), show));
						}
						statusMenu.addAction(clearCustomsAction);
					}
				}
			}
		});
	}

	private SimpleAction<PresencePage> createAction(final String label, final String actionId, final ImageResource iconType, final String status, final Show show) {
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

	public SimpleAction<PresencePage> createCustomAction(final String title, final String id, final ImageResource icon, final String status, final Show show) {
		return new SimpleAction<PresencePage>(title, id, icon) {
			@Override
			public void execute(final PresencePage target) {
				setPresence(status, show);
			}
		};
	}

	private void createDefActions() {
		defaultActions.add(createAction(UserMessages.msg.available(), ACTION_ID_AVAILABLE, IconsBundle.bundle.buddyIconOn(), null, Show.chat));
		defaultActions.add(createAction(UserMessages.msg.availableCustom(), ACTION_ID_AVAILABLE_CUSTOM, IconsBundle.bundle.buddyIconOn(), "", Show.chat));
		defaultActions.add(createAction(UserMessages.msg.busy(), ACTION_ID_BUSY, IconsBundle.bundle.buddyIconDnd(), null, Show.dnd));
		defaultActions.add(createAction(UserMessages.msg.busyCustom(), ACTION_ID_BUSY_CUSTOM, IconsBundle.bundle.buddyIconDnd(), "", Show.dnd));
		clearCustomsAction = new SimpleAction<PresencePage>(UserMessages.msg.clearCustom(), ACTION_CLEAR_CUSTOM, IconsBundle.bundle.closeIcon()) {
			@Override
			public void execute(final PresencePage target) {
				storedPresenceManager.clearAll();
				updateMenu();
			}
		};
	}

	@Override
	public void saveData() {
	}

	private void setPresence(final String status, final Show show) {
		showPresence(status, show);
		if (statusNotEmpty(status)) {
			storedPresenceManager.add(status, show, new PrivateStorageResponseHandler() {
				@Override
				public void onStorageResponse(PrivateStorageResponseEvent event) {
					IQ response = event.getResponseIQ();
					if (IQ.isSuccess(response)) {
						updateMenu();
					}
				}
			});
		}
		final Presence presence = presenceManager.getOwnPresence();
		presence.setStatus(status);
		presence.setShow(show);
		presenceManager.changeOwnPresence(presence);
	}

	private void setShowIcon(final Show show) {
		display.setStatusIcon(PresenceIcon.get(true, show));
	}

	@Override
	public void showData() {
		final Presence presence = presenceManager.getOwnPresence();
		showPresence(presence.getStatus(), presence.getShow());
	}

	private void showPresence(final String status, final Show show) {
		setShowIcon(show);
		display.getStatusText().setText(status);
	}

	private boolean statusNotEmpty(final String status) {
		return (status != null) && !status.isEmpty();
	}

	private void updateMenu() {
		statusMenu.clear();
		for (final SimpleAction<PresencePage> action : defaultActions) {
			statusMenu.addAction(action);
		}
		addCustomPresenceActions();
	}

	private void updateStatus(final PresenceDisplay display) {
		setPresence(display.getStatusText().getText(), presenceManager.getOwnPresence().getShow());
	}

}
