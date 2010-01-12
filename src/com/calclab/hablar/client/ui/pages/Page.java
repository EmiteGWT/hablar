package com.calclab.hablar.client.ui.pages;

import com.calclab.suco.client.events.Listener;

public interface Page {

    PageHeader getHeader();

    void onClose(Listener<PageWidget> closeListener);

    void onOpenChanged(Listener<PageWidget> openListener);

    void onStatusChanged(Listener<String> statusListener);

    void setOpen(boolean isOpen);

}
