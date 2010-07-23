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
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Manages a graphical selection list made with divs instead of select/options.
 */
public class SelectionList extends Composite implements HasSelectionHandlers<Selectable>,
	HasValueChangeHandlers<List<Selectable>> {

    private static final String SELECTED_CLASS = "hablar-selectedBackground";

    private static SelectionListUiBinder uiBinder = GWT.create(SelectionListUiBinder.class);

    private final List<Selectable> selectables;

    private final Set<Selectable> selectedItems;

    private final List<HandlerRegistration> registrations;

    private String mode;

    @UiField
    FlowPanel selectionPanel;

    private final class MultipleSelectionHandler implements ClickHandler {
	private final Selectable selectable;

	private MultipleSelectionHandler(Selectable selectable) {
	    this.selectable = selectable;
	}

	@Override
	public void onClick(ClickEvent event) {
	    if (selectedItems.contains(selectable)) {
		selectable.getWidget().removeStyleName(SELECTED_CLASS);
		selectedItems.remove(selectable);
	    } else {
		selectable.getWidget().addStyleName(SELECTED_CLASS);
		selectedItems.add(selectable);
	    }
	    SelectionEvent.fire(SelectionList.this, selectable);
	}
    }

    private final class SingleSelectionHandler implements ClickHandler {
	private final Selectable selectable;

	private SingleSelectionHandler(Selectable selectable) {
	    this.selectable = selectable;
	}

	@Override
	public void onClick(ClickEvent event) {
	    selectedItems.clear();
	    for (Selectable item : selectables) {
		item.getWidget().removeStyleName(SELECTED_CLASS);
	    }
	    selectable.getWidget().addStyleName(SELECTED_CLASS);
	    selectedItems.add(selectable);
	    SelectionEvent.fire(SelectionList.this, selectable);
	}
    }

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

    public void setMode(String mode) {
	this.mode = mode;
    }

    public void clear() {
	boolean empty = selectables.isEmpty();
	selectables.clear();
	selectedItems.clear();
	selectionPanel.clear();
	if (!empty) {
	    ValueChangeEvent.fire(this, selectables);
	}
    }

    public void add(Selectable selectable) {
	if (addSingle(selectable)) {
	    ValueChangeEvent.fire(this, selectables);
	}
    }

    public void remove(Selectable selectable) {
	if (removeSingle(selectable)) {
	    ValueChangeEvent.fire(this, selectables);
	}
    }

    public void addAll(Collection<Selectable> selectables) {
	boolean added = false;
	for (Selectable selectable : selectables) {
	    added |= addSingle(selectable);
	}
	if (added) {
	    ValueChangeEvent.fire(this, this.selectables);
	}
    }

    public void removeAll(Collection<Selectable> selectables) {
	boolean removed = false;
	for (Selectable selectable : selectables) {
	    removed |= removeSingle(selectable);
	}
	if (removed) {
	    ValueChangeEvent.fire(this, this.selectables);
	}
    }

    public Set<Selectable> getSelectedSelectables() {
	return selectedItems;
    }

    public Selectable getSelectedSelectable() {
	if (!selectedItems.isEmpty()) {
	    return selectedItems.iterator().next();
	}
	return null;
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

    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<Selectable> handler) {
	return super.addHandler(handler, SelectionEvent.getType());
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<Selectable>> handler) {
	return super.addHandler(handler, ValueChangeEvent.getType());
    }

    private boolean addSingle(Selectable selectable) {
	int position = Collections.binarySearch(selectables, selectable);
	if (position < 0) {
	    int index = -(position + 1);
	    selectables.add(index, selectable);
	    selectionPanel.insert(selectable.getWidget(), index);
	    registrations.add(index, registerClick(selectable));
	    return true;
	}
	return false;
    }

    private boolean removeSingle(Selectable selectable) {
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
	    return true;
	}
	return false;
    }

    private List<Object> getItemsFromSelectables(Collection<Selectable> collection) {
	List<Object> items = new ArrayList<Object>();
	for (Selectable selectable : collection) {
	    items.add(selectable.getItem());
	}
	return items;
    }

    private HandlerRegistration registerClick(final Selectable selectable) {
	HandlerRegistration registration = selectable.getAction().addClickHandler(getHandler(selectable));
	return registration;
    }

    private ClickHandler getHandler(final Selectable selectable) {
	if ("single".equals(mode)) {
	    return new SingleSelectionHandler(selectable);
	}
	return new MultipleSelectionHandler(selectable);
    }
}
