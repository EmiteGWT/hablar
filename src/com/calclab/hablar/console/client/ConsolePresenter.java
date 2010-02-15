package com.calclab.hablar.console.client;

import com.calclab.emite.core.client.bosh.Connection;
import com.calclab.emite.core.client.packet.IPacket;
import com.calclab.emite.core.client.services.gwt.GWTXMLService;
import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.HasText;

public class ConsolePresenter extends PagePresenter<ConsoleDisplay> {
    private static final String TYPE = "Logger";

    public ConsolePresenter(final HablarEventBus eventBus, final ConsoleDisplay display) {
	super(TYPE, eventBus, display);
	model.init(HablarIcons.get(IconType.console), "Console");
	setVisibility(Visibility.hidden);
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

	display.getInput().addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(final KeyDownEvent event) {
		if (event.getNativeKeyCode() == 13) {
		    final HasText input = display.getInputText();
		    if (event.getNativeEvent().getCtrlKey()) {
			// With ctrl + Enter insert a newline (Can be useful
			// also for chat -implemented in other clients-)
			final String newText = input.getText() + "\n";
			input.setText(newText);
			display.setInputCursorPos(newText.length());
		    } else {
			final String packet = input.getText();
			if (session.getState() == State.ready && !packet.isEmpty()) {
			    session.send(GWTXMLService.toXML(packet));
			    input.setText("");
			}
		    }
		}
	    }
	});
    }
}
