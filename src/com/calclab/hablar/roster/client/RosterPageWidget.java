package com.calclab.hablar.roster.client;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.menu.PopupMenu;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.calclab.hablar.basic.client.ui.page.PageWidget;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class RosterPageWidget extends PageWidget implements RosterView {

    public static interface Style extends CssResource {
	String action();
    }

    interface RosterWidgetUiBinder extends UiBinder<Widget, RosterPageWidget> {
    }

    public static final String DEBUGID_ROSTER_MENU = "Roster-ItemMenu";

    public static final String ID = "RosterPage";
    public static final String DISABLED_LABEL = "RosterPage-DisableLabel";
    private static RosterWidgetUiBinder uiBinder = GWT.create(RosterWidgetUiBinder.class);

    @UiField
    LayoutPanel roster;

    @UiField
    Style style;

    @UiField
    FlowPanel list, actions, disabledPanel;

    @UiField
    Label disabledLabel;

    @UiField
    ScrollPanel scroll;

    private final RosterLogic logic;

    private PopupMenu<RosterItem> itemMenu;

    public RosterPageWidget(HablarEventBus hablarEventBus, Visibility visibility) {
	super(hablarEventBus, RosterView.TYPE, visibility, false);
	setId(ID);
	initWidget(uiBinder.createAndBindUi(this));
	final Msg i18n = Suco.get(Msg.class);

	logic = new RosterLogic(this);
	setHeaderTitle(i18n.contacts());
	disabledLabel.setText(i18n.rosterDisabled());
	disabledLabel.ensureDebugId(DISABLED_LABEL);
	setHeaderIconClass(HablarIcons.get(HablarIcons.IconType.roster));
    }

    @Override
    public void addAction(final String iconStyle, final String id, final ClickHandler clickHandler) {
	final Label label = new Label();
	label.getElement().addClassName(style.action());
	label.getElement().addClassName(iconStyle);
	label.addClickHandler(clickHandler);
	label.ensureDebugId(id);
	actions.add(label);
    }

    @Override
    public RosterItemView createItemView() {
	final RosterItemWidget view = new RosterItemWidget(logic);
	list.add(view);
	return view;
    }

    @Override
    public PopupMenuView<RosterItem> getItemMenu() {
	if (itemMenu == null) {
	    itemMenu = new PopupMenu<RosterItem>(DEBUGID_ROSTER_MENU);
	}
	return itemMenu;
    }

    @Override
    public void removeItemView(final RosterItemView view) {
	list.remove((Widget) view);
    }

    public void setActive(final boolean active) {
	GWT.log("ROSTER: " + active, null);
	if (active) {
	    roster.setWidgetTopHeight(actions, 0, Unit.PX, 20, Unit.PX);
	    roster.setWidgetTopHeight(disabledPanel, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetTopBottom(scroll, 20, Unit.PX, 0, Unit.PX);
	} else {
	    roster.setWidgetTopHeight(actions, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetTopBottom(disabledPanel, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetBottomHeight(scroll, 0, Unit.PX, 0, Unit.PX);
	}
	roster.animate(active ? 500 : 0);
    }
}
