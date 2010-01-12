package com.calclab.hablar.client.ui.page;

import com.calclab.suco.client.events.Listener;

public interface Page {

    public static enum Visibility {
	open, closed, hidden
    }

    PageHeader getHeader();

    void onClose(Listener<PageWidget> closeListener);

    void onOpenChanged(Listener<PageWidget> openListener);

    void onStatusChanged(Listener<PageWidget> statusListener);

    void setVisibility(Visibility visibility);

}
