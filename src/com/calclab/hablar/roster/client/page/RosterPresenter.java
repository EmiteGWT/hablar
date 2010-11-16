package com.calclab.hablar.roster.client.page;

import static com.calclab.hablar.roster.client.HablarRoster.i18n;

import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.im.client.roster.RosterGroup;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.im.client.roster.events.RosterGroupChangedEvent;
import com.calclab.emite.im.client.roster.events.RosterGroupChangedHandler;
import com.calclab.emite.im.client.roster.events.RosterItemChangedEvent;
import com.calclab.emite.im.client.roster.events.RosterItemChangedHandler;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.RosterBasicActions;
import com.calclab.hablar.roster.client.RosterConfig;
import com.calclab.hablar.roster.client.groups.RosterGroupDisplay;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * The roster page presenter.
 * 
 * @see RosterPage
 */
public class RosterPresenter extends PagePresenter<RosterDisplay> implements
		RosterPage {
	public static final String ROSTER_MESSAGE = "RosterMessage";
	private static int index = 0;

	public static RosterPage asRoster(final Page<?> page) {
		if (TYPE.equals(page.getType())) {
			return (RosterPage) page;
		} else {
			return null;
		}
	}

	private boolean active;
	private final XmppRoster roster;
	private final RosterBasicActions basicActions;
	private final Menu<RosterItemPresenter> itemMenu;
	private final HashMap<String, RosterGroupPresenter> groupPresenters;
	private final Menu<RosterGroupPresenter> groupMenu;
	private final RosterConfig rosterConfig;

	public RosterPresenter(final HablarEventBus eventBus,
			final RosterDisplay display, final RosterConfig rosterConfig) {
		super(TYPE, "" + ++index, eventBus, display);
		roster = Suco.get(XmppRoster.class);

		if (rosterConfig.rosterMenuActions != null) {
			basicActions = rosterConfig.rosterMenuActions;
		} else {
			basicActions = new RosterBasicActions(eventBus);
		}
		
		this.rosterConfig = rosterConfig;

		groupPresenters = new HashMap<String, RosterGroupPresenter>();
		active = true;

		itemMenu = new Menu<RosterItemPresenter>(display
				.newRosterItemMenuDisplay("hablar-RosterItemMenu"));
		groupMenu = new Menu<RosterGroupPresenter>(display
				.newRosterGroupMenuDisplay("hablar-RosterGroupMenu"));

		addRosterListeners();
		addSessionListeners();
		final String title = i18n().contacts();
		getState().init(Icons.ROSTER, title, title);

	}

	@Override
	public void addAction(final Action<RosterPage> action) {

		display.createAction(action).addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				action.execute(RosterPresenter.this);
			}
		});

	}

	@Override
	public void addHighPriorityActions() {
		basicActions.addHighPriorityActions(this);
	}

	@Override
	public void addLowPriorityActions() {
		basicActions.addLowPriorityActions(this);
	}

	@Override
	public Menu<RosterGroupPresenter> getGroupMenu() {
		return groupMenu;
	}

	@Override
	public Menu<RosterItemPresenter> getItemMenu() {
		return itemMenu;
	}

	private void addRosterListeners() {
		roster.addRosterItemChangedHandler(new RosterItemChangedHandler() {
			
			@Override
			public void onRosterItemChanged(RosterItemChangedEvent event) {
				if(event.isAdded()) {
					final String message = i18n().userAdded(
							event.getRosterItem().getJID().toString());
					fireMessage(message);
				} else if(event.isRemoved()) {
					final String message = i18n().userRemoved(
							event.getRosterItem().getJID().toString());
					fireMessage(message);
				} else if(event.isModified()) {
					for(RosterGroupPresenter groupPresenter : groupPresenters.values()) {
						groupPresenter.rosterItemChanged(event.getRosterItem());
					}
				}
			}
		});
		
		roster.addRosterGroupChangedHandler(new RosterGroupChangedHandler() {
			
			@Override
			public void onGroupChanged(RosterGroupChangedEvent event) {
				if(event.isAdded()) {
					createGroup(event.getRosterGroup());
				} else if(event.isRemoved()) {
					display.remove(groupPresenters.get(event.getRosterGroup().getName()));
				}
			}
		});

		if (roster.isRosterReady()) {
			loadRoster();
		}
	}

	private void addSessionListeners() {
		final Session session = Suco.get(Session.class);
		session.onStateChanged(new Listener<Session>() {
			@Override
			public void onEvent(final Session session) {
				final State state = session.getState();
				setSessionState(state);
			}

		});
		setSessionState(session.getState());
	}

	private void createGroup(final RosterGroup group) {
		final RosterGroupDisplay groupDisplay = display.newRosterGroupDisplay();
		final RosterGroupPresenter presenter = new RosterGroupPresenter(group,
				itemMenu, groupDisplay, rosterConfig);
		groupPresenters.put(group.getName(), presenter);
		display.addGroup(presenter, groupMenu);
	}

	private void fireMessage(final String message) {
		eventBus.fireEvent(new UserMessageEvent(this, message, ROSTER_MESSAGE));
	}

	private void loadRoster() {
		GWT.log("LOAD ROSTER");
		groupPresenters.clear();
		for (final RosterGroup group : roster.getRosterGroups()) {
			createGroup(group);
		}
	}

	private void setSessionState(final State state) {
		final boolean isActive = state == State.ready;
		if (active != isActive) {
			active = isActive;
			display.setActive(active);
		}
	}

}
