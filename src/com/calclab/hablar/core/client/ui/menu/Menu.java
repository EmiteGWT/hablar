package com.calclab.hablar.core.client.ui.menu;

import java.util.ArrayList;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.google.gwt.user.client.Command;

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
	display.show(left - WIDTH, top + Y_OFFSET);
    }

}
