package com.calclab.hablar.basic.client.presence;

import com.calclab.hablar.basic.client.ui.styles.HablarStyles;

public interface PresenceView {

    void setIcon(HablarStyles.IconType icon);

    void setName(String name);

    void setStatusBoxFocus(boolean focus);

    void setStatusBoxVisible(boolean visible);

    void setStatusMessage(String statusMessage);

    void setStatusMessageVisible(boolean visible);
}
