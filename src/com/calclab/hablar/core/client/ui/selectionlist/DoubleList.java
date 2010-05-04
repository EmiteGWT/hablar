/**
 *
 */
package com.calclab.hablar.core.client.ui.selectionlist;

import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This widget represents a double list, i.e. a selectable list of elements divided in two groups:
 * <ul>
 * <li>the available items;</li>
 * <li>the selected items.</li>
 * </ul>
 * Between them there are four buttons to command the movement between the two lists.
 */
public class DoubleList extends Composite {

    @UiField
    Label availableLabel, selectedLabel;

    @UiField
    SelectionList availableList, selectedList;

    @UiField
    Button selectAll, selectSome, deselectSome, deselectAll;

    private static DoubleListUiBinder uiBinder = GWT.create(DoubleListUiBinder.class);

    interface DoubleListUiBinder extends UiBinder<Widget, DoubleList> {
    }

    public DoubleList() {
	initWidget(uiBinder.createAndBindUi(this));
	selectAll.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Set<Selectable> selectables = availableList.getAndRemoveAllSelectables();
		selectedList.addAll(selectables);
	    }
	});

	selectSome.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Set<Selectable> selectables = availableList.getAndRemoveSelectedSelectables();
		selectedList.addAll(selectables);
	    }
	});

	deselectSome.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Set<Selectable> selectables = selectedList.getAndRemoveSelectedSelectables();
		availableList.addAll(selectables);
	    }
	});

	deselectAll.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Set<Selectable> selectables = selectedList.getAndRemoveAllSelectables();
		availableList.addAll(selectables);
	    }
	});
    }
//
//    public void initialize(Collection<Selectable> allSelectables, Collection<Selectable> selectedSelectables) {
//	availableList.clear();
//	selectedList.clear();
//	Set<Selectable> availableSelectables = new TreeSet<Selectable>(allSelectables);
//	availableSelectables.removeAll(selectedSelectables);
//	availableList.addAll(availableSelectables);
//	selectedList.addAll(selectedSelectables);
//    }

    public void add(Selectable selectable) {
	availableList.add(selectable);
    }

    public void clear() {
	availableList.clear();
	selectedList.clear();

    }

    public List<Object> getSelectedItems() {
	return selectedList.getItems();
    }

    public void setAvailableLabelText(String text) {
	availableLabel.setText(text);
    }

    public void setSelectedLabelText(String text) {
	selectedLabel.setText(text);
    }

    public void setSelectAllTooltip(String text) {
	selectAll.setTitle(text);
    }

    public void setSelectSomeTooltip(String text) {
	selectSome.setTitle(text);
    }

    public void setDeselectAllTooltip(String text) {
	deselectAll.setTitle(text);
    }

    public void setDeselectSomeTooltip(String text) {
	deselectSome.setTitle(text);
    }
}
