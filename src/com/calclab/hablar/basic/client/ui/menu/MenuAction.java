package com.calclab.hablar.basic.client.ui.menu;

public abstract class MenuAction<T> {

    private final String html;
    private final String id;

    public MenuAction(final String html, final String id) {
	this.html = html;
	this.id = id;
    }

    public abstract void execute(T target);

    public String getHTML() {
	return html;
    }

    public String getId() {
	return id;
    }
}
