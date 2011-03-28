package com.calclab.hablar.search.client.page;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface SearchResultItemDisplay extends Display {

    HasText getJid();

    HasText getName();

    HasClickHandlers getMenu();

    HasClickHandlers getBuddyIcon();

    HasClickHandlers getClickableJid();

    HasClickHandlers getClickableName();
}
