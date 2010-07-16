package com.calclab.hablar.usergroups.client.changegroups;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.validators.IsEmpty;

public class GroupSelectorPresenter implements Presenter<GroupSelectorDisplay> {

    private final GroupSelectorDisplay display;

    public GroupSelectorPresenter(final GroupSelectorDisplay display) {
	this.display = display;
    }

    @Override
    public GroupSelectorDisplay getDisplay() {
	return display;
    }

    public String getName() {
	return display.getEditableName().getText();
    }

    public boolean isSelected() {
	return display.getSelected().getValue() == true && !IsEmpty.is(display.getEditableName().getText());
    }

    public void setProperties(final String name, final boolean editable, final boolean selected) {
	display.setIcon(Icons.get(Icons.ROSTER));
	display.setEditable(editable);
	display.getSelected().setValue(selected);
	display.getStaticName().setText(name);
	display.getEditableName().setText(name);
    }

}
