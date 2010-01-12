package com.calclab.hablar.client.ui.pages;

public interface Pages {
    public static enum Position {
	normal, visible, WEST, hidden
    }

    void add(Page page, Pages.Position position);

    boolean hasPage(Page page);

    /**
     * Hide a page (do not show it in the pages ui).
     * 
     * @param page
     */
    void hide(Page page);

    /**
     * Open the given page. If the page is hidden, it whill be show
     * 
     * @param page
     */
    void open(Page page);
}
