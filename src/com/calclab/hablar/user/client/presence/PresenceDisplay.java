package com.calclab.hablar.user.client.presence;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.user.client.ui.HasText;

public interface PresenceDisplay extends Display {
    HasText getStatus();

}
