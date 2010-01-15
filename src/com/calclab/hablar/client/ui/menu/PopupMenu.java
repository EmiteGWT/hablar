package com.calclab.hablar.client.ui.menu;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;

public class PopupMenu<T> extends PopupPanel implements PopupMenuView<T> {
    private final MenuBar bar;
    private T target;

    public PopupMenu(MenuAction<T>... actions) {
	super(true);
	bar = new MenuBar(true);
	for (MenuAction<T> a : actions) {
	    final MenuAction<T> action = a;
	    bar.addItem(action.getHTML(), true, new Command() {
		@Override
		public void execute() {
		    PopupMenu.this.hide();
		    action.execute(target);
		}
	    });
	}
	setWidget(bar);
    }

    @Override
    public void setTarget(T target) {
	this.target = target;
    }

    @Override
    public void show(int left, int top) {
	setPopupPosition(left, top);
	show();
    }

}
