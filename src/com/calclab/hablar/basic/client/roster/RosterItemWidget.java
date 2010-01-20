package com.calclab.hablar.basic.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.lists.ListItemWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

class RosterItemWidget extends ListItemWidget implements RosterItemView {

    interface RosterItemWidgetUiBinder extends UiBinder<Widget, RosterItemWidget> {
    }

    private static RosterItemWidgetUiBinder uiBinder = GWT.create(RosterItemWidgetUiBinder.class);

    @UiField
    Label name, status, icon, jid, menu;

    private RosterItem item;

    private String iconStyle;

    public RosterItemWidget(final RosterLogic logic) {
	super(logic);
	initWidget(uiBinder.createAndBindUi(this));
	menu.addStyleName(HablarIcons.get(HablarIcons.IconType.menu));
	super.setMenu(menu);
	setMenuVisible(false);
    }

    @Override
    public RosterItem getItem() {
	return item;
    }

    @Override
    public void setIcon(final HablarIcons.IconType iconType) {
	if (this.iconStyle != null) {
	    icon.removeStyleName(iconStyle);
	}
	this.iconStyle = HablarIcons.get(iconType);
	icon.getElement().addClassName(iconStyle);
	icon.addStyleName(iconStyle);
    }

    @Override
    public void setItem(final RosterItem item) {
	this.item = item;
    }

    @Override
    public void setJID(final String jidString) {
	jid.setText(jidString);
    }

    @Override
    public void setMenuDebugId(final String debugId) {
	menu.ensureDebugId(debugId);
    }

    @Override
    public void setName(final String nameString) {
	name.setText(nameString);
    }

    @Override
    public void setNameDebugId(final String debugId) {
	name.ensureDebugId(debugId);
    }

    @Override
    public void setStatus(final String status) {
	this.status.setText(status);
    }

    @Override
    public void setStatusVisible(final boolean visible) {
	this.status.setVisible(visible);
    }
}
