package com.calclab.hablar.client;

import com.google.gwt.user.client.ui.DialogBox;

public class HablarDialog extends DialogBox {

    public HablarDialog(HablarConfig config) {
	this.setText("Hablar");
	HablarWidget widget = new HablarWidget(config);
	widget.setSize("300px", "400px");
	this.add(widget);
    }
}
