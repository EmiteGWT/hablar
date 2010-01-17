package com.calclab.hablar.client.search;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.client.ui.menu.MenuAction;
import com.calclab.hablar.client.ui.menu.PopupMenu;
import com.calclab.hablar.client.ui.menu.PopupMenuView;
import com.calclab.hablar.client.ui.page.PageWidget;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchPageWidget extends PageWidget implements SearchView {

    interface SearchPageUiBinder extends UiBinder<Widget, SearchPageWidget> {
    }

    static interface Styles extends CssResource {
	String alert();

	String focus();

	String info();

	String success();

	String unfocus();

    }

    public static final String ID = "SearchPageWidget";
    public static final String TERM = ID + "-term";
    public static final String MESSAGE = ID + "-message";

    private static SearchPageUiBinder uiBinder = GWT.create(SearchPageUiBinder.class);
    private final SearchLogic logic;

    @UiField
    LayoutPanel self;

    @UiField
    ScrollPanel scroll;

    @UiField
    FlowPanel results, messagePanel;

    @UiField
    public Styles style;

    @UiField
    TextBox term;

    @UiField
    Label message;

    public SearchPageWidget() {
	super(true);
	super.setId(ID);
	initWidget(uiBinder.createAndBindUi(this));
	term.ensureDebugId(TERM);
	message.ensureDebugId(MESSAGE);
	logic = new SearchLogic(this);
	setHeaderTitle("Search users");
	setHeaderIconClass(HablarStyles.get(HablarStyles.IconType.search));
    }

    public void addResult(final SearchResultItem item) {
	results.add(new SearchResultItemWidget(logic, item));
    }

    public void clearResults() {
	results.clear();
    }

    @Override
    public PopupMenuView<SearchResultView> createMenu(final MenuAction<SearchResultView>... actions) {
	final PopupMenu<SearchResultView> popupMenu = new PopupMenu<SearchResultView>(actions);
	return popupMenu;
    }

    @UiHandler("term")
    public void onFocus(final FocusEvent evt) {
	term.setText("");
	term.getElement().removeClassName(style.unfocus());
	term.getElement().addClassName(style.focus());
    }

    @UiHandler("term")
    public void onSearch(final ChangeEvent evt) {
	logic.search(term.getText());
	term.setText("");
    }

    @Override
    public void showMessage(final String body, final Level level) {
	self.setWidgetTopHeight(messagePanel, 0, PX, 22, PX);
	self.setWidgetTopBottom(scroll, 22, PX, 33, PX);
	self.animate(250);

	message.setText(body);
	message.getElement().addClassName(getStyle(level));
    }

    private String getStyle(final Level level) {
	String value = null;
	if (level == Level.success) {
	    value = style.success();
	} else if (level == Level.error) {
	    value = style.alert();
	} else {
	    value = style.success();
	}
	return value;
    }

}
