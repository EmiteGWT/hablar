package com.calclab.hablar.basic.client.ui.page;


public interface PageView {

    public static enum Visibility {
	focused, notFocused, closed
    }

    PageHeader getHeader();

    /**
     * Get the page type (Roster, Chat...)
     * 
     * @return
     */
    String getPageType();

    String getStatusMessage();

    /**
     * Ask the page about it's current visibility state
     * 
     * @param visibility
     */
    Visibility getVisibility();

    /**
     * Change the icon class of the header
     * 
     * @param iconStyle
     */
    void setHeaderIconClass(String iconStyle);

    /**
     * Changes the header title of this page
     * 
     * @param title
     *            the new title
     */
    void setHeaderTitle(String title);

    /**
     * Change the status message of this page
     * 
     * @param status
     */
    void setStatusMessage(String status);

    /**
     * Inform the page about it's current visibility state
     * 
     * @param visibility
     */
    void setVisibility(Visibility visibility);

}
