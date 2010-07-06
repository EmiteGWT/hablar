package com.calclab.hablar.signals.client.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.calclab.hablar.signals.client.SignalMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SignalsPreferencesWidget extends Composite implements SignalsPreferencesDisplay {

    private static SignalMessages messages;

    public static void setMessages(final SignalMessages messages) {
	SignalsPreferencesWidget.messages = messages;
    }

    public static SignalMessages i18n() {
	return messages;
    }

    interface SignalsPreferencesWidgetUiBinder extends UiBinder<Widget, SignalsPreferencesWidget> {
    }
    
    /**
     * I'm not sure if this is how I should be doing this.
     */
    private class NotifierChangeHandler implements HasValueChangeHandlers<SignalsPreferencesDisplay.NotifierSelectChange> {

	HandlerManager handlerManager;
	
	public NotifierChangeHandler(final Object source) {
	    handlerManager = new HandlerManager(source);
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<NotifierSelectChange> handler) {
	    return handlerManager.addHandler(ValueChangeEvent.getType(), handler);
	}

	@Override
	public void fireEvent(final GwtEvent<?> event) {
	    handlerManager.fireEvent(event);
	}
    }

    private static SignalsPreferencesWidgetUiBinder uiBinder = GWT.create(SignalsPreferencesWidgetUiBinder.class);

    @UiField
    CheckBox titleSignals, incomingNotifications, rosterNotifications;

    @UiField
    SpanElement title;
    @UiField
    Label loadingMessage;
    @UiField
    DivElement form, loading;
    @UiField
    Image icon;
    @UiField
    HeadingElement methodsMessage;
    @UiField
    FlowPanel methodsPanel;
    
    HashMap<String, CheckBox> notifierMap;

    NotifierChangeHandler notifierChangeHandler;
    
    public SignalsPreferencesWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	titleSignals.ensureDebugId("SignalsPreferencesWidget-titleSignals");
	incomingNotifications.ensureDebugId("SignalsPreferencesWidget-incomingNotifications");
	rosterNotifications.ensureDebugId("SignalsPreferencesWidget-rosterNotifications");
	title.setInnerText(i18n().preferencesPageTitle());
	titleSignals.setText(i18n().showUnreadConversations());
	incomingNotifications.setText(i18n().showIncomingMessages());
	rosterNotifications.setText(i18n().showRoster());
	methodsMessage.setInnerText(i18n().notificationMethods());
	notifierMap = new HashMap<String, CheckBox>();
	
	notifierChangeHandler = new NotifierChangeHandler(this); 
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasValue<Boolean> getIncomingNotifications() {
	return incomingNotifications;
    }

    @Override
    public HasText getLoading() {
	return loadingMessage;
    }

    @Override
    public HasValue<Boolean> getRosterNotifications() {
	return rosterNotifications;
    }

    @Override
    public HasValue<Boolean> getTitleSignals() {
	return titleSignals;
    }

    @Override
    public void setFormVisible(final boolean visible) {
	setVisible(form, visible);
    }

    @Override
    public void setLoadingVisible(final boolean visible) {
	setVisible(loading, visible);
    }

    @Override
    public void addNotifier(final String id, final String name) {
	CheckBox notifierCheckbox = new CheckBox(name);
	notifierMap.put(id, notifierCheckbox);
	
	methodsPanel.add(notifierCheckbox);

	// Add an event handler to the new checkbox to fire the notifier change handler
	notifierCheckbox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
	    @Override
	    public void onValueChange(ValueChangeEvent<Boolean> event) {
		SignalsPreferencesDisplay.NotifierSelectChange notifierSelectChange = new SignalsPreferencesDisplay.NotifierSelectChange(id, event.getValue());
		
		ValueChangeEvent.fire(notifierChangeHandler, notifierSelectChange);
	    }
	});
    }

    @Override
    public List<String> getSelectedNotifiers() {
	ArrayList<String> ret = new ArrayList<String>();
	
	for(Entry<String, CheckBox> entry : notifierMap.entrySet()) {
	    if(entry.getValue().getValue()) {
		ret.add(entry.getKey());
	    }
	}
	
	return ret;
    }

    @Override
    public void setNotifierSelected(String id, boolean selected) {
	CheckBox checkbox = notifierMap.get(id);
	
	if(checkbox != null) {
	    checkbox.setValue(selected);
	}
    }

    @Override
    public HasValueChangeHandlers<NotifierSelectChange> getNotifiersChangeHandler() {
	return notifierChangeHandler;
    }

    @Override
    public boolean isNotifierSelected(String id) {
	CheckBox checkbox = notifierMap.get(id);
	
	if(checkbox != null) {
	    return checkbox.getValue();
	}
	
	return false;
    }   
}
