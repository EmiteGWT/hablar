package com.calclab.hablar.client.ui.pages;

import com.calclab.hablar.client.ui.page.Page;
import com.calclab.hablar.client.ui.page.Page.Visibility;
import com.calclab.suco.client.events.Listener;

public interface Pages {

    void add(Page page, Visibility initialVisibility);

    boolean hasPage(Page page);

    /**
     * Hide a page (do not show it in the pages ui).
     * 
     * @param page
     *            the page to be hidden
     */
    void hide(Page page);

    /**
     * Add a listener to know when a status message changed in any of the pages
     * 
     * @param listener
     */
    void onStatusMessageChanged(Listener<Page> listener);

    /**
     * Open the given page. If the page is hidden, it whill be show.
     * 
     * @param page
     *            the page to be showed
     */
    void open(Page page);

    /**
     * Show a previously hidden page
     * 
     * @param page
     */
    void show(Page page);
}
