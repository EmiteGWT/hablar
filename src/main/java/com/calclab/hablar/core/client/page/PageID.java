package com.calclab.hablar.core.client.page;

/**
 * A opaque PageID object
 */
public class PageID {

    private static int index = 0;

    public static PageID create() {
	return new PageID(++index);
    }

    private final int id;

    private PageID(int id) {
	this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof PageID)
	    return ((PageID) obj).id == id;
	else
	    return false;
    }

    @Override
    public int hashCode() {
	return id;
    }

    @Override
    public String toString() {
	return "PageID-" + id;
    }
}
