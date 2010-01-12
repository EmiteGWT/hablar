package com.calclab.hablar.client;

import com.google.gwt.user.client.ui.DialogBox;

public class HablarDialog extends DialogBox {

    public HablarDialog(HablarConfig config) {
	this.setText("Hablar");
	HablarWidget widget = new HablarWidget(config);
	widget.setSize("500px", "300px");
	this.add(widget);
    }
}
