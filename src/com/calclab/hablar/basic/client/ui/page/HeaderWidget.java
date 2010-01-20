package com.calclab.hablar.basic.client.ui.page;

import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The header of the page. The header has text, icon, close button (optional)
 * and active state. Each PageWidget has one PageHeader. The PageWidget creates
 * the header.
 */
class HeaderWidget extends Composite implements PageHeader {

    interface DefaultStyles extends HeaderStyles {
    }

    interface StackHeaderUiBinder extends UiBinder<Widget, HeaderWidget> {
    }

    private static StackHeaderUiBinder uiBinder = GWT.create(StackHeaderUiBinder.class);

    @UiField
    Label title, close, icon;
    @UiField
    FlowPanel header;

    @UiField
    DefaultStyles defaultStyles;

    private String iconClass;
    private HeaderStyles currentStyles;

    private Visibility visibility;

    private PageLogic logic;

    public HeaderWidget(final boolean closeable) {
	final Widget element = uiBinder.createAndBindUi(this);
	initWidget(element);
	close.setVisible(closeable);
	this.currentStyles = defaultStyles;
	// UIUtils.setTextSelectionEnabled(title.getElement(), false);
    }

    @Override
    public void requestFocus() {
	if (visibility == Visibility.notFocused) {
	    header.getElement().addClassName(currentStyles.requestFocus());
	    header.getElement().removeClassName(currentStyles.closed());
	}
    }

    public void setIconClass(final String iconClass) {
	if (this.iconClass != null) {
	    icon.getElement().removeClassName(this.iconClass);
	}
	icon.getElement().addClassName(iconClass);
	this.iconClass = iconClass;

    }

    public void setId(final String id) {
	title.ensureDebugId(id);
    }

    @Override
    public void setLogic(PageLogic logic) {
	this.logic = logic;
    }

    public void setStyles(final HeaderStyles styles) {
	this.currentStyles = styles;
	applyStyles();
    }

    @Override
    public void setVisibility(final Visibility visibility) {
	this.visibility = visibility;
	applyStyles();
    }

    private void applyStyles() {
	header.getElement().removeClassName(currentStyles.requestFocus());
	if (visibility == Visibility.focused) {
	    header.getElement().removeClassName(currentStyles.closed());
	    header.getElement().addClassName(currentStyles.open());
	} else if (visibility == Visibility.notFocused) {
	    header.getElement().removeClassName(currentStyles.open());
	    header.getElement().addClassName(currentStyles.closed());
	}
    }

    @UiHandler("title")
    void handleActivateTitle(final ClickEvent event) {
	logic.fireOpen();
    }

    @UiHandler("close")
    void handleClose(final ClickEvent event) {
	logic.fireClose();
    }

    @UiHandler("icon")
    void onIconClicked(final ClickEvent event) {
	logic.fireOpen();
    }

    void setHeaderTitle(final String title) {
	this.title.setText(title);
    }

}
