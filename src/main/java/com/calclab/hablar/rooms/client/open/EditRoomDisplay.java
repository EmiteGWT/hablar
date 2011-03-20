package com.calclab.hablar.rooms.client.open;

import java.util.List;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.core.client.validators.HasState;
import com.calclab.hablar.roster.client.selection.RosterItemSelector;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

public interface EditRoomDisplay extends Display, RosterItemSelector {

    void clearList();

    SelectRosterItemDisplay createItem();

    HasState<Boolean> getAcceptEnabled();

    HasClickHandlers getCancel();

    HasClickHandlers getInvite();

    HasText getMessage();

    HasValue<String> getRoomName();

    HasValue<List<Selectable>> getSelectionList();

    HasText getRoomNameError();

    HasKeyDownHandlers getRoomNameKeys();

    void setAcceptText(String string);

    void setPageTitle(String text);

    void setRoomNameEnabled(boolean enabled);

}
