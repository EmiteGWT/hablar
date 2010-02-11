package com.calclab.hablar.roster.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionHandler;
import com.calclab.emite.im.client.roster.SubscriptionHandler.Behaviour;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.roster.client.page.RosterWidget;
import com.calclab.hablar.roster.client.ui.move.MoveToGroupPresenter;
import com.calclab.hablar.roster.client.ui.move.MoveToGroupWidget;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;

public class HablarRoster implements EntryPoint {
    private static final String MOVE_ACTION_ID = "HablarRoster-moveToGroupAction";

    public static void install(final Hablar hablar) {
	final SubscriptionHandler subscriptionHandler = Suco.get(SubscriptionHandler.class);
	subscriptionHandler.setBehaviour(Behaviour.acceptAll);

	final RosterPage roster = new RosterPresenter(hablar.getEventBus(), new RosterWidget());
	roster.setVisibility(Visibility.notFocused);
	hablar.addPage(roster);

	new RosterBasicActions(roster);

	final MoveToGroupPresenter movePage = new MoveToGroupPresenter(hablar.getEventBus(), new MoveToGroupWidget());
	hablar.addPage(movePage, OverlayContainer.ROL);

	final String icon = HablarIcons.get(IconType.buddy);
	final String name = "Move to a group";

	roster.getItemMenu().addAction(new SimpleAction<RosterItem>(name, MOVE_ACTION_ID, icon) {
	    @Override
	    public void execute(final RosterItem target) {
		movePage.setItem(target);
		movePage.requestVisibility(Visibility.focused);
	    }
	});

	final Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {

	    @Override
	    public void onEvent(final Session session) {
		setState(roster, session.getState());
	    }
	});
	setState(roster, session.getState());
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    private static void setState(final RosterPage roster, final State state) {
	if (state == State.ready) {
	    roster.requestVisibility(Visibility.focused);
	}
    }

    @Override
    public void onModuleLoad() {

    }

}
