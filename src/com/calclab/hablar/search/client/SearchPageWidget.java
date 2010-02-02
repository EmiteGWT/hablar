package com.calclab.hablar.search.client;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.EventBus;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.menu.PopupMenu;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.calclab.hablar.basic.client.ui.page.PageWidget;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchPageWidget extends PageWidget implements SearchPageView {

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
    public static final String TERM_DEB_ID = "SearchPageWidget-term";
    public static final String MESSAGE_DEB_ID = "SearchPageWidget-message";
    public static final String TYPE = "Search";

    private static SearchPageUiBinder uiBinder = GWT.create(SearchPageUiBinder.class);

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
    @UiField
    Button search;
    private final SearchPageLogic logic;

    public SearchPageWidget(EventBus eventBus, Visibility visibility, boolean closeable) {
	super(eventBus, TYPE, visibility, closeable);
	super.setId(ID);
	initWidget(uiBinder.createAndBindUi(this));
	final Msg i18n = Suco.get(Msg.class);
	term.ensureDebugId(TERM_DEB_ID);
	term.setText(i18n.typeToSearchUsers());
	message.ensureDebugId(MESSAGE_DEB_ID);
	setHeaderTitle("Search users");
	setHeaderIconClass(HablarIcons.get(HablarIcons.IconType.search));
	logic = new SearchPageLogic(eventBus, this);
    }

    public void addResult(final SearchResultItem item) {
	results.add(new SearchResultItemWidget(logic, item));
    }

    public void clearResults() {
	results.clear();
    }

    @Override
    public PopupMenuView<SearchResultItemView> createMenu(final String debugId) {
	final PopupMenu<SearchResultItemView> popupMenu = new PopupMenu<SearchResultItemView>(debugId);
	return popupMenu;
    }

    @Override
    public HasClickHandlers getSearchButton() {
	return search;
    }

    @Override
    public HasChangeHandlers getSearchChange() {
	return term;
    }

    @Override
    public Focusable getSearchFocus() {
	return term;
    }

    @Override
    public HasText getSearchTerm() {
	return term;
    }

    @UiHandler("term")
    public void onFocus(final FocusEvent evt) {
	term.setText("");
	term.getElement().removeClassName(style.unfocus());
	term.getElement().addClassName(style.focus());
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
