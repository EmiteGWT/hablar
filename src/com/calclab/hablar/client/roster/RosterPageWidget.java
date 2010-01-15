package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.menu.MenuAction;
import com.calclab.hablar.client.ui.menu.PopupMenu;
import com.calclab.hablar.client.ui.menu.PopupMenuView;
import com.calclab.hablar.client.ui.page.PageWidget;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class RosterPageWidget extends PageWidget implements RosterView {

    public static interface Style extends CssResource {

	String action();

    }

    interface RosterWidgetUiBinder extends UiBinder<Widget, RosterPageWidget> {
    }

    public static final String ID = "RosterPage";

    private static RosterWidgetUiBinder uiBinder = GWT.create(RosterWidgetUiBinder.class);

    @UiField
    LayoutPanel roster;

    @UiField
    Style style;

    @UiField
    FlowPanel list, actions, disabledPanel;

    private final RosterLogic logic;

    public RosterPageWidget() {
	super(false);
	setId(ID);
	initWidget(uiBinder.createAndBindUi(this));
	logic = new RosterLogic(this);
	setHeaderTitle("Roster");
	setHeaderIconClass(HablarStyles.get(IconType.roster));
    }

    public void addAction(final String iconStyle, final ClickHandler clickHandler) {
	final Label label = new Label();
	label.getElement().addClassName(style.action());
	label.getElement().addClassName(iconStyle);
	label.addClickHandler(clickHandler);
	actions.add(label);
    }

    @Override
    public RosterItemView createItemView() {
	RosterItemWidget view = new RosterItemWidget(logic);
	list.add(view);
	return view;
    }

    @Override
    public PopupMenuView<RosterItem> createMenu(MenuAction<RosterItem>... actions) {
	return new PopupMenu<RosterItem>(actions);
    }

    @Override
    public void removeItemView(RosterItemView view) {
	list.remove((Widget) view);
    }

    public void setActive(final boolean active) {
	GWT.log("ROSTER: " + active, null);
	if (active) {
	    roster.setWidgetTopHeight(actions, 0, Unit.PX, 20, Unit.PX);
	    roster.setWidgetTopHeight(disabledPanel, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetTopBottom(list, 20, Unit.PX, 0, Unit.PX);
	} else {
	    roster.setWidgetTopHeight(actions, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetTopBottom(disabledPanel, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetBottomHeight(list, 0, Unit.PX, 0, Unit.PX);
	}
	roster.animate(active ? 500 : 0);
    }
}
