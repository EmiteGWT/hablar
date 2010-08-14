package com.calclab.hablar.core.client.ui.actions;

import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ActionWidget extends Composite implements HasClickHandlers {

    interface ActionWidgetUiBinder extends UiBinder<Widget, ActionWidget> {

    }

    @UiField
    Image image;

    private static ActionWidgetUiBinder uiBinder = GWT.create(ActionWidgetUiBinder.class);

    @UiConstructor
    public ActionWidget(final Action<?> action) {
	initWidget(uiBinder.createAndBindUi(this));
	addStyleName("hablar-ActionWidget");
	addStyleName("hablar-lightBackground");
	Icons.set(image, action.getIcon());
	ensureDebugId(action.getId());
	setTitle(action.getDescription());
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
	return addDomHandler(handler, ClickEvent.getType());
    }
}
