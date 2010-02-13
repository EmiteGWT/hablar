package com.calclab.hablar.logger.client;

import com.calclab.emite.core.client.bosh.Connection;
import com.calclab.emite.core.client.packet.IPacket;
import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class LoggerPage extends PagePresenter<LoggerDisplay> {
    private static final String TYPE = "Logger";

    public LoggerPage(final HablarEventBus eventBus, final LoggerDisplay display) {
	super(TYPE, eventBus, display);
	model.init(HablarIcons.get(IconType.roster), "Logger");
	setVisibility(Visibility.notFocused);
	model.setCloseable(true);

	final Session session = Suco.get(Session.class);

	final Connection connection = Suco.get(Connection.class);
	connection.onStanzaReceived(new Listener<IPacket>() {
	    @Override
	    public void onEvent(final IPacket parameter) {
		display.add(parameter.toString(), "input", session.getState().toString());
	    }
	});
	connection.onStanzaSent(new Listener<IPacket>() {
	    @Override
	    public void onEvent(final IPacket parameter) {
		display.add(parameter.toString(), "output", session.getState().toString());
	    }
	});

	display.getClear().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		display.clear();
	    }
	});
    }
}
