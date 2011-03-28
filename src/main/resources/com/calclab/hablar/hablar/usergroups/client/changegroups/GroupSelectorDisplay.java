package com.calclab.hablar.usergroups.client.changegroups;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

public interface GroupSelectorDisplay extends Display {

    HasText getEditableName();

    HasValue<Boolean> getSelected();

    HasText getStaticName();

    void setEditable(boolean editable);

    void setIcon(ImageResource icon);
}
