package com.calclab.hablar.client.ui.menu;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.UIObject;

public class PopupMenu<T> extends PopupPanel implements PopupMenuView<T> {
    private final MenuBar bar;
    private T target;
    private boolean visible;

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
	visible = false;
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
    public void setTarget(T target) {
	this.target = target;
    }

    @Override
    public void show(int left, int top) {
	this.visible = true;
	setPopupPosition(left, top);
	show();
    }

    @Override
    public void showRelativeToMenu(UIObject target) {
	this.visible = true;
	super.showRelativeTo(target);
    }

}
