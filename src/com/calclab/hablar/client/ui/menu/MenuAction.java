package com.calclab.hablar.client.ui.menu;

public abstract class MenuAction<T> {

    private final String html;

    public MenuAction(String html) {
	this.html = html;
    }

    public abstract void execute(T target);

    public String getHTML() {
	return html;
    }
}
