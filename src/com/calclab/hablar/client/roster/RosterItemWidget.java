package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.lists.ListItemWiget;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

class RosterItemWidget extends ListItemWiget implements RosterItemView {

    interface RosterItemWidgetUiBinder extends UiBinder<Widget, RosterItemWidget> {
    }

    private static RosterItemWidgetUiBinder uiBinder = GWT.create(RosterItemWidgetUiBinder.class);

    @UiField
    Label name, status, icon, jid, menu;

    private RosterItem item;

    private String iconStyle;

    public RosterItemWidget(RosterLogic logic) {
	super(logic);
	initWidget(uiBinder.createAndBindUi(this));
	menu.addStyleName(HablarStyles.get(HablarStyles.IconType.menu));
	super.setMenu(menu);
	setMenuVisible(false);
    }

    @Override
    public RosterItem getItem() {
	return item;
    }

    @Override
    public void setIcon(HablarStyles.IconType iconType) {
	if (this.iconStyle != null) {
	    icon.removeStyleName(iconStyle);
	}
	this.iconStyle = HablarStyles.get(iconType);
	icon.getElement().addClassName(iconStyle);
	icon.addStyleName(iconStyle);
    }

    @Override
    public void setItem(RosterItem item) {
	this.item = item;
    }

    @Override
    public void setJID(String jidString) {
	jid.setText(jidString);
    }

    @Override
    public void setName(String nameString) {
	name.setText(nameString);
    }

    @Override
    public void setStatus(String status) {
	this.status.setText(status);
    }

    @Override
    public void setStatusVisible(boolean visible) {
	this.status.setVisible(visible);
    }
}
