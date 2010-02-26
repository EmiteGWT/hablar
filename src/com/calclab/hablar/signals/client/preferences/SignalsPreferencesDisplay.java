package com.calclab.hablar.signals.client.preferences;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

public interface SignalsPreferencesDisplay extends Display {

    HasValue<Boolean> getIncomingNotifications();

    HasText getLoading();

    HasValue<Boolean> getRosterNotifications();

    HasValue<Boolean> getTitleSignals();

    void setFormVisible(boolean visible);

    void setLoadingVisible(boolean visible);

}
