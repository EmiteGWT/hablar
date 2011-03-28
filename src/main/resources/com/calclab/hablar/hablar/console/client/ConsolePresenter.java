package com.calclab.hablar.console.client;

import com.calclab.emite.core.client.conn.StanzaEvent;
import com.calclab.emite.core.client.conn.StanzaHandler;
import com.calclab.emite.core.client.conn.XmppConnection;
import com.calclab.emite.core.client.services.gwt.GWTXMLService;
import com.calclab.emite.core.client.xmpp.session.SessionStates;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.HasText;

public class ConsolePresenter extends PagePresenter<ConsoleDisplay> {
    private static final String TYPE = "Logger";

    public ConsolePresenter(final XmppConnection connection, final XmppSession session, final HablarEventBus eventBus,
	    final ConsoleDisplay display) {
	super(TYPE, eventBus, display);
	model.init(Icons.CONSOLE, "Console", "Console");
	setVisibility(Visibility.hidden);
	model.setCloseable(true);

	connection.addStanzaReceivedHandler(new StanzaHandler() {
	    @Override
	    public void onStanza(final StanzaEvent event) {
		display.add(event.getStanza().toString(), "input", session.getSessionState());
	    }
	});
	connection.addStanzaSentHandler(new StanzaHandler() {
	    @Override
	    public void onStanza(final StanzaEvent event) {
		display.add(event.getStanza().toString(), "output", session.getSessionState());
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
			final boolean isReady = SessionStates.ready.equals(session.getSessionState());
			if (isReady && !packet.isEmpty()) {
			    session.send(GWTXMLService.toXML(packet));
			    input.setText("");
			}
		    }
		}
	    }
	});
    }
}
