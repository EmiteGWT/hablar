package com.calclab.hablar.core.client.ui.menu;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;

public class PopupMenu<T> extends PopupPanel implements PopupMenuView<T> {
    private final MenuBar bar;
    private T target;
    private boolean visible;

    public PopupMenu(final String debugId) {
	super(true);
	super.ensureDebugId(debugId);
	setStyleName("hablar-PopupPanel");
	bar = new MenuBar(true);
	setWidget(bar);
	visible = false;
    }

    @Override
    public void addAction(final Action<T> action) {
	final MenuItem addedItem = bar.addItem(action.getName(), true, new Command() {
	    @Override
	    public void execute() {
		PopupMenu.this.hide();
		action.execute(target);
	    }
	});
	addedItem.ensureDebugId(action.getId());
    }

    @Override
    public void hide() {
	visible = false;
	super.hide();
    }

    @Override
    public boolean isVisible() {
	return visible;
    }

    @Override
    public void setTarget(final T target) {
	this.target = target;
    }

    @Override
    public void show(final int left, final int top) {
	this.visible = true;
	setPopupPosition(left, top);
	show();
    }

}
