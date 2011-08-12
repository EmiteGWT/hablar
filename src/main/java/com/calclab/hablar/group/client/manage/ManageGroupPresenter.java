package com.calclab.hablar.group.client.manage;

import java.util.List;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.core.client.validators.CompositeValidatorChecker;
import com.calclab.hablar.core.client.validators.ListNotEmptyValidator;
import com.calclab.hablar.core.client.validators.Validators;
import com.calclab.hablar.group.client.GroupMessages;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

/**
 * Abstract class that displays a form to manage a group in the roster.
 */
public abstract class ManageGroupPresenter extends PagePresenter<ManageGroupDisplay> {

	private CompositeValidatorChecker groupNameValidator;

	public ManageGroupPresenter(HablarEventBus eventBus, final ManageGroupDisplay display, String pageTitle) {
		super("ManageGroup", eventBus, display);

		display.getCancel().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				requestVisibility(Visibility.hidden);
			}
		});

		display.setPageTitle(pageTitle);
		groupNameValidator = new CompositeValidatorChecker(display.getGroupNameError(), display.getAcceptEnabled());
		groupNameValidator.add(display.getGroupName(), Validators.notEmpty(GroupMessages.msg.groupNameEmptyErrorMessage()));
		groupNameValidator.add(display.getSelectionList(), new ListNotEmptyValidator<Selectable>(GroupMessages.msg.selectionEmptyErrorMessage()));
		display.getGroupNameKeys().addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				Scheduler.get().scheduleDeferred(groupNameValidator.getScheduledCommand());
			}
		});
		display.getSelectionList().addValueChangeHandler(new ValueChangeHandler<List<Selectable>>() {

			@Override
			public void onValueChange(ValueChangeEvent<List<Selectable>> event) {
				Scheduler.get().scheduleDeferred(groupNameValidator.getScheduledCommand());
			}
		});
	}

	@Override
	protected void onBeforeFocus() {
		preloadForm();
		groupNameValidator.validate();
	}

	protected abstract void preloadForm();
}
