package com.calclab.hablar.client;

public class HablarConfig {

    public static enum Layout {
	accordion, tabs, dock
    }

    /**
     * Choose a layout
     */
    public Layout layout;

    /**
     * Show or not login panel
     */
    public boolean hasLogin;

    /**
     * Show or not roster panel
     */
    public boolean hasRoster;

    /**
     * Show or not search panel
     */
    public boolean hasSearch;

    /**
     * If not null, show 'hablar' inside the div with the id given
     */
    public String inline;

    /**
     * Width
     */
    public String width;

    /**
     * Height
     */
    public String height;

}
