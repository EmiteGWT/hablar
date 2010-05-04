package com.calclab.hablar.core.client.ui.selectionlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Manages a graphical selection list made with divs instead of select/options.
 */
public class SelectionList extends Composite {

    private static final String SELECTED_CLASS = "hablar-selectedBackground";

    private static SelectionListUiBinder uiBinder = GWT.create(SelectionListUiBinder.class);

    private List<Selectable> selectables;

    private Set<Selectable> selectedItems;

    private List<HandlerRegistration> registrations;

    @UiField
    FlowPanel selectionPanel;

    interface SelectionListUiBinder extends UiBinder<Widget, SelectionList> {
    }

    public SelectionList() {
	this(null);
    }

    public SelectionList(Collection<Selectable> selectables) {
	initWidget(uiBinder.createAndBindUi(this));
	registrations = new ArrayList<HandlerRegistration>();
	selectedItems = new TreeSet<Selectable>();
	this.selectables = new ArrayList<Selectable>();
	if (selectables != null) {
	    Collections.sort(this.selectables);
	    for (final Selectable selectable : this.selectables) {
		Widget widget = selectable.getWidget();
		selectionPanel.add(widget);
		registrations.add(registerClick(selectable));
	    }
	}
    }

    public void clear() {
	selectables.clear();
	selectedItems.clear();
	selectionPanel.clear();
    }

    public void add(Selectable selectable) {
	int position = Collections.binarySearch(selectables, selectable);
	if (position < 0) {
	    int index = -(position + 1);
	    selectables.add(index, selectable);
	    selectionPanel.insert(selectable.getWidget(), index);
	    registrations.add(index, registerClick(selectable));
	}
    }

    public void remove(Selectable selectable) {
	selectedItems.remove(selectable);
	int position = Collections.binarySearch(selectables, selectable);
	if (position >= 0) {
	    selectable.getWidget().removeStyleName(SELECTED_CLASS);
	    selectables.remove(position);
	    selectionPanel.remove(position);
	    HandlerRegistration registration = registrations.remove(position);
	    if (registration != null) {
		registration.removeHandler();
	    }
	}
    }

    public void addAll(Collection<Selectable> selectables) {
	for (Selectable selectable : selectables) {
	    add(selectable);
	}
    }

    public void removeAll(Collection<Selectable> selectables) {
	for (Selectable selectable : selectables) {
	    remove(selectable);
	}
    }

    public Set<Selectable> getSelectedSelectables() {
	return selectedItems;
    }

    public Set<Selectable> getAndRemoveSelectedSelectables() {
	Set<Selectable> retValue = new TreeSet<Selectable>(selectedItems);
	removeAll(retValue);
	return retValue;
    }

    public Set<Selectable> getAndRemoveAllSelectables() {
	Set<Selectable> retValue = new TreeSet<Selectable>(selectables);
	removeAll(retValue);
	return retValue;
    }

    public List<Object> getSelectedItems() {
	return getItemsFromSelectables(selectedItems);
    }

    public List<Selectable> getSelectables() {
	return selectables;
    }

    public List<Object> getItems() {
	return getItemsFromSelectables(selectables);
    }

    private List<Object> getItemsFromSelectables(Collection<Selectable> collection) {
	List<Object> items = new ArrayList<Object>();
	for (Selectable selectable: collection) {
	    items.add(selectable.getItem());
	}
	return items;
    }

    private HandlerRegistration registerClick(final Selectable selectable) {
	HandlerRegistration registration = selectable.getAction().addClickHandler(new ClickHandler() {

	@Override
	public void onClick(ClickEvent event) {
	    if (selectedItems.contains(selectable)) {
		selectable.getWidget().removeStyleName(SELECTED_CLASS);
		selectedItems.remove(selectable);
	    } else {
		selectable.getWidget().addStyleName(SELECTED_CLASS);
		selectedItems.add(selectable);
	    }
	}
	});
	return registration;
    }
}
