package com.calclab.hablar.basic.client.ui.pages;

import com.calclab.hablar.basic.client.ui.page.Page;
import com.calclab.suco.client.events.Listener;

public interface Pages {

    void add(Page page);

    /**
     * Close the given page.
     * 
     * @param page
     *            the page to be closed
     * @return true if closed, false if the page was not open or it was the only
     *         visible page
     */
    boolean close(Page page);

    /**
     * 
     * @param page
     * @return
     */
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
}
