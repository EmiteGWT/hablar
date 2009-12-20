package com.calclab.hablar.client;

import com.google.gwt.user.client.ui.DialogBox;

public class HablarDialog extends DialogBox {

    public HablarDialog() {
	this.setText("Hablar");
	HablarWidget widget = new HablarWidget();
	widget.setSize("300px", "400px");
	this.add(widget);
    }
}
