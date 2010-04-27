package com.calclab.hablar.core.client.ui.menu;

import java.util.ArrayList;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;

public class Menu<T> implements Presenter<MenuDisplay<T>> {

    private static final int WIDTH = 169;
    private static final int Y_OFFSET = 8;
    private final MenuDisplay<T> display;
    private T target;
    private final ArrayList<Action<T>> actions;

    public Menu(final MenuDisplay<T> display) {
	this.display = display;
	this.actions = new ArrayList<Action<T>>();
    }

    public void addAction(final Action<T> action) {
	actions.add(action);
	display.addAction(action, new Command() {
	    @Override
	    public void execute() {
		display.hide();
		action.execute(target);
	    }
	});
    }

    public void clear() {
	actions.clear();
	display.clearActions();
    }

    @Override
    public MenuDisplay<T> getDisplay() {
	return display;
    }

    public void removeAction(final Action<T> action) {
	actions.remove(action);
	display.removeAction(action);
    }

    public void setTarget(final T item) {
	this.target = item;

    }

    public void show(final int left, final int top) {
	for (final Action<T> action : actions) {
	    display.setActionVisible(action, action.isApplicable(target));
	}

	int menuLeft = left;

	// If the menu goes off the right edge of the window, make the anchor
	// point the top right
	// This should hopefully mean the menu is always displayed on the screen
	if ((left + WIDTH) > Window.getClientWidth()) {
	    menuLeft = left - WIDTH;
	}

	display.show(menuLeft, top + Y_OFFSET);

	// This next bit is just in case the rendered width is different from the specified width - there might be some extra css or something
	int width = display.asWidget().getOffsetWidth();

	if (width != WIDTH) {
	    menuLeft = left;

	    // If the menu goes off the right edge of the window, make the
	    // anchor point the top right
	    // This should hopefully mean the menu is always displayed on the
	    // screen
	    if ((left + width) > Window.getClientWidth()) {
		menuLeft = left - width;
	    }
	    
	    display.show(menuLeft, top + Y_OFFSET);
	}

    }

}
