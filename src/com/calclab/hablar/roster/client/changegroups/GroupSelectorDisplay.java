package com.calclab.hablar.roster.client.changegroups;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

public interface GroupSelectorDisplay extends Display {

    HasText getEditableName();

    HasValue<Boolean> getSelected();

    HasText getStaticName();

    void setEditable(boolean editable);

    void setIconStyle(String style);
}
