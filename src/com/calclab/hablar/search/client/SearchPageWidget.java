package com.calclab.hablar.search.client;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.menu.PopupMenu;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.calclab.hablar.basic.client.ui.page.PageWidget;
import com.calclab.suco.client.Suco;
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
    private final SearchPageLogic logic;

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

    public SearchPageWidget(Visibility visibility, boolean closeable) {
	super(TYPE, visibility, closeable);
	super.setId(ID);
	initWidget(uiBinder.createAndBindUi(this));
	final Msg i18n = Suco.get(Msg.class);
	term.ensureDebugId(TERM_DEB_ID);
	term.setText(i18n.typeToSearchUsers());
	message.ensureDebugId(MESSAGE_DEB_ID);
	logic = new SearchPageLogic(this);
	setHeaderTitle("Search users");
	setHeaderIconClass(HablarIcons.get(HablarIcons.IconType.search));
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
