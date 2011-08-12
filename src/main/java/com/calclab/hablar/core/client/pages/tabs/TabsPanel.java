package com.calclab.hablar.core.client.pages.tabs;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.HasBeforeSelectionHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.WidgetCollection;

/**
 * A modified version of TabLayoutPanel to avoid auto-open tabs.
 * 
 * A panel that represents a tabbed set of pages, each of which contains another
 * widget. Its child widgets are shown as the user selects the various tabs
 * associated with them. The tabs can contain arbitrary text, HTML, or widgets.
 * 
 * <p>
 * This widget will <em>only</em> work in standards mode, which requires that
 * the HTML page in which it is run have an explicit &lt;!DOCTYPE&gt;
 * declaration.
 * </p>
 * 
 * <h3>CSS Style Rules</h3>
 * <dl>
 * <dt>.gwt-TabLayoutPanel
 * <dd>the panel itself
 * <dt>.gwt-TabLayoutPanel .gwt-TabLayoutPanelTabs
 * <dd>the tab bar element
 * <dt>.gwt-TabLayoutPanel .gwt-TabLayoutPanelTab
 * <dd>an individual tab
 * <dt>.gwt-TabLayoutPanel .gwt-TabLayoutPanelTabInner
 * <dd>an element nested in each tab (useful for styling)
 * </dl>
 * 
 * <p>
 * <h3>Example</h3>
 * {@example com.google.gwt.examples.TabLayoutPanelExample}
 * 
 * <h3>Use in UiBinder Templates</h3>
 * <p>
 * A TabLayoutPanel element in a {@link com.google.gwt.uibinder.client.UiBinder
 * UiBinder} template must have a <code>barHeight</code> attribute with a double
 * value, and may have a <code>barUnit</code> attribute with a
 * {@link com.google.gwt.dom.client.Style.Unit Style.Unit} value.
 * <code>barUnit</code> defaults to PX.
 * <p>
 * The children of a TabLayoutPanel element are laid out in &lt;g:tab> elements.
 * Each tab can have one widget child and one of two types of header elements. A
 * &lt;g:header> element can hold html, or a &lt;g:customHeader> element can
 * hold a widget. (Note that the tags of the header elements are not
 * capitalized. This is meant to signal that the head is not a runtime object,
 * and so cannot have a <code>ui:field</code> attribute.)
 * <p>
 * For example:
 * 
 * <pre>
 * &lt;g:TabLayoutPanel barUnit='PX' barHeight='3'>
 *  &lt;g:tab>
 *    &lt;g:header size='7'>&lt;b>HTML&lt;/b> header&lt;/g:header>
 *    &lt;g:Label>able&lt;/g:Label>
 *  &lt;/g:tab>
 *  &lt;g:tab>
 *    &lt;g:customHeader size='7'>
 *      &lt;g:Label>Custom header&lt;/g:Label>
 *    &lt;/g:customHeader>
 *    &lt;g:Label>baker&lt;/g:Label>
 *  &lt;/g:tab>
 * &lt;/g:TabLayoutPanel>
 * </pre>
 */
public class TabsPanel extends ResizeComposite implements HasWidgets, ProvidesResize, IndexedPanel, HasBeforeSelectionHandlers<Integer>,
		HasSelectionHandlers<Integer> {

	private static class Tab extends SimplePanel {
		private Element inner;

		public Tab(Widget child) {
			super(Document.get().createDivElement());
			getElement().appendChild(inner = Document.get().createDivElement());

			setWidget(child);
			setStyleName("gwt-TabLayoutPanelTab");
			inner.setClassName("gwt-TabLayoutPanelTabInner");

			// TODO: float:left may not be enough. If there are tabs of
			// differing
			// heights, the shorter ones will top-align, rather than
			// bottom-align,
			// which is what we would want. display:inline-block fixes this, but
			// needs lots of cross-browser hacks to work properly.
			getElement().getStyle().setFloat(Style.Float.LEFT);
		}

		@Override
		public Widget asWidget() {
			return this;
		}

		public void setSelected(boolean selected) {
			if (selected) {
				addStyleDependentName("selected");
			} else {
				removeStyleDependentName("selected");
			}
		}

		@Override
		protected com.google.gwt.user.client.Element getContainerElement() {
			return inner.cast();
		}
	}

	private static final int BIG_ENOUGH_TO_NOT_WRAP = 16384;

	private final WidgetCollection children = new WidgetCollection(this);
	private final FlowPanel tabBar = new FlowPanel();
	private final ArrayList<Tab> tabs = new ArrayList<Tab>();
	private final double barHeight;
	private final Unit barUnit;
	private final LayoutPanel panel;
	private int selectedIndex = -1;
	private final ExtendedScrollPanel scroll;
	private final TabsMenuWidget menu;

	/**
	 * Creates an empty tab panel.
	 * 
	 * @param barHeight
	 *            the size of the tab bar
	 * @param barUnit
	 *            the unit in which the tab bar size is specified
	 */
	public TabsPanel(double barHeight, Unit barUnit) {
		this.barHeight = barHeight;
		this.barUnit = barUnit;

		panel = new LayoutPanel();
		initWidget(panel);
		menu = new TabsMenuWidget();
		scroll = new ExtendedScrollPanel();

		scroll.add(tabBar);
		panel.add(scroll);
		panel.add(menu);
		panel.setWidgetLeftRight(scroll, 0, Unit.PX, 19, Unit.PX);
		panel.setWidgetTopHeight(scroll, 0, Unit.PX, barHeight, barUnit);
		panel.setWidgetRightWidth(menu, 0, Unit.PX, 19, Unit.PX);
		panel.setWidgetVerticalPosition(scroll, Alignment.END);
		panel.setWidgetHorizontalPosition(menu, Alignment.END);

		// Make the tab bar extremely wide so that tabs themselves never wrap.
		// (Its layout container is overflow:hidden)
		tabBar.getElement().getStyle().setWidth(BIG_ENOUGH_TO_NOT_WRAP, Unit.PX);

		tabBar.setStyleName("gwt-TabLayoutPanelTabs");
		setStyleName("gwt-TabLayoutPanel");

		scroll.setStyleName("overflowhidden");
		DOM.setStyleAttribute(scroll.getElement(), "overflow", "hidden");
	}

	@Override
	public void add(Widget w) {
		insert(w, getWidgetCount());
	}

	/**
	 * Adds a widget to the panel. If the Widget is already attached, it will be
	 * moved to the right-most index.
	 * 
	 * @param child
	 *            the widget to be added
	 * @param text
	 *            the text to be shown on its tab
	 */
	public void add(Widget child, String text) {
		insert(child, text, getWidgetCount());
	}

	/**
	 * Adds a widget to the panel. If the Widget is already attached, it will be
	 * moved to the right-most index.
	 * 
	 * @param child
	 *            the widget to be added
	 * @param text
	 *            the text to be shown on its tab
	 * @param asHtml
	 *            <code>true</code> to treat the specified text as HTML
	 */
	public void add(Widget child, String text, boolean asHtml) {
		insert(child, text, asHtml, getWidgetCount());
	}

	/**
	 * Adds a widget to the panel. If the Widget is already attached, it will be
	 * moved to the right-most index.
	 * 
	 * @param child
	 *            the widget to be added
	 * @param tab
	 *            the widget to be placed in the associated tab
	 */
	public void add(Widget child, Widget tab) {
		insert(child, tab, getWidgetCount());
	}

	@Override
	public HandlerRegistration addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler) {
		return addHandler(handler, BeforeSelectionEvent.getType());
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

	@Override
	public void clear() {
		Iterator<Widget> it = iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	public TabsMenuWidget getMenu() {
		return menu;
	}

	/**
	 * Gets the index of the currently-selected tab.
	 * 
	 * @return the selected index, or <code>-1</code> if none is selected.
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}

	/**
	 * Gets the widget in the tab at the given index.
	 * 
	 * @param index
	 *            the index of the tab to be retrieved
	 * @return the tab's widget
	 */
	public Widget getTabWidget(int index) {
		checkIndex(index);
		return tabs.get(index).getWidget();
	}

	/**
	 * Gets the widget in the tab associated with the given child widget.
	 * 
	 * @param child
	 *            the child whose tab is to be retrieved
	 * @return the tab's widget
	 */
	public Widget getTabWidget(Widget child) {
		checkChild(child);
		return getTabWidget(getWidgetIndex(child));
	}

	@Override
	public Widget getWidget(int index) {
		checkIndex(index);
		return children.get(index);
	}

	@Override
	public int getWidgetCount() {
		return children.size();
	}

	@Override
	public int getWidgetIndex(Widget child) {
		return children.indexOf(child);
	}

	/**
	 * Inserts a widget into the panel. If the Widget is already attached, it
	 * will be moved to the requested index.
	 * 
	 * @param child
	 *            the widget to be added
	 * @param beforeIndex
	 *            the index before which it will be inserted
	 */
	public void insert(Widget child, int beforeIndex) {
		insert(child, "", beforeIndex);
	}

	/**
	 * Inserts a widget into the panel. If the Widget is already attached, it
	 * will be moved to the requested index.
	 * 
	 * @param child
	 *            the widget to be added
	 * @param text
	 *            the text to be shown on its tab
	 * @param asHtml
	 *            <code>true</code> to treat the specified text as HTML
	 * @param beforeIndex
	 *            the index before which it will be inserted
	 */
	public void insert(Widget child, String text, boolean asHtml, int beforeIndex) {
		Widget contents;
		if (asHtml) {
			contents = new HTML(text);
		} else {
			contents = new Label(text);
		}
		insert(child, contents, beforeIndex);
	}

	/**
	 * Inserts a widget into the panel. If the Widget is already attached, it
	 * will be moved to the requested index.
	 * 
	 * @param child
	 *            the widget to be added
	 * @param text
	 *            the text to be shown on its tab
	 * @param beforeIndex
	 *            the index before which it will be inserted
	 */
	public void insert(Widget child, String text, int beforeIndex) {
		insert(child, text, false, beforeIndex);
	}

	/**
	 * Inserts a widget into the panel. If the Widget is already attached, it
	 * will be moved to the requested index.
	 * 
	 * @param child
	 *            the widget to be added
	 * @param tab
	 *            the widget to be placed in the associated tab
	 * @param beforeIndex
	 *            the index before which it will be inserted
	 */
	public void insert(Widget child, Widget tab, int beforeIndex) {
		insert(child, new Tab(tab), beforeIndex);
	}

	@Override
	public Iterator<Widget> iterator() {
		return children.iterator();
	}

	@Override
	public boolean remove(int index) {
		if ((index < 0) || (index >= getWidgetCount())) {
			return false;
		}

		tabBar.remove(index);
		panel.remove(children.get(index));

		children.remove(index);
		tabs.remove(index);

		if (index == selectedIndex) {
			// If the selected tab is being removed, select the first tab (if
			// there
			// is one).
			selectedIndex = -1;
			if (getWidgetCount() > 0) {
				selectTab(0);
			}
		} else if (index < selectedIndex) {
			// If the selectedIndex is greater than the one being removed, it
			// needs
			// to be adjusted.
			--selectedIndex;
		}
		return true;
	}

	@Override
	public boolean remove(Widget w) {
		int index = children.indexOf(w);
		if (index == -1) {
			return false;
		}

		return remove(index);
	}

	/**
	 * Programmatically selects the specified tab.
	 * 
	 * @param index
	 *            the index of the tab to be selected
	 */
	public void selectTab(int index) {
		checkIndex(index);
		if (index == selectedIndex) {
			return;
		}

		// Fire the before selection event, giving the recipients a chance to
		// cancel the selection.
		BeforeSelectionEvent<Integer> event = BeforeSelectionEvent.fire(this, index);
		if ((event != null) && event.isCanceled()) {
			return;
		}

		// Update the tabs being selected and unselected.
		if (selectedIndex != -1) {
			Widget child = children.get(selectedIndex);
			Element container = panel.getWidgetContainerElement(child);

			// container.getStyle().setDisplay(Display.NONE);
			container.getStyle().setVisibility(com.google.gwt.dom.client.Style.Visibility.HIDDEN);

			// child.setVisible(false);
			child.getElement().getStyle().setVisibility(com.google.gwt.dom.client.Style.Visibility.HIDDEN);

			tabs.get(selectedIndex).setSelected(false);
		}

		Widget child = children.get(index);
		Element container = panel.getWidgetContainerElement(child);
		layoutChild(child);
		// container.getStyle().clearDisplay();
		container.getStyle().setVisibility(com.google.gwt.dom.client.Style.Visibility.VISIBLE);

		child.setVisible(true);
		child.getElement().getStyle().setVisibility(com.google.gwt.dom.client.Style.Visibility.VISIBLE);
		final Tab tab = tabs.get(index);
		tab.setSelected(true);
		scroll.ensureVisible(tab);
		selectedIndex = index;

		// Fire the selection event.
		SelectionEvent.fire(this, index);
	}

	/**
	 * Programmatically selects the specified tab.
	 * 
	 * @param child
	 *            the child whose tab is to be selected
	 */
	public void selectTab(Widget child) {
		selectTab(getWidgetIndex(child));
	}

	/**
	 * Sets a tab's HTML contents.
	 * 
	 * Use care when setting an object's HTML; it is an easy way to expose
	 * script-based security problems. Consider using
	 * {@link #setTabText(int, String)} whenever possible.
	 * 
	 * @param index
	 *            the index of the tab whose HTML is to be set
	 * @param html
	 *            the tab's new HTML contents
	 */
	public void setTabHTML(int index, String html) {
		checkIndex(index);
		tabs.get(index).setWidget(new HTML(html));
	}

	/**
	 * Sets a tab's text contents.
	 * 
	 * @param index
	 *            the index of the tab whose text is to be set
	 * @param text
	 *            the object's new text
	 */
	public void setTabText(int index, String text) {
		checkIndex(index);
		tabs.get(index).setWidget(new Label(text));
	}

	private void checkChild(Widget child) {
		assert children.contains(child);
	}

	private void checkIndex(int index) {
		assert (index >= 0) && (index < children.size()) : "Index out of bounds";
	}

	private void insert(final Widget child, Tab tab, int beforeIndex) {
		assert (beforeIndex >= 0) && (beforeIndex <= getWidgetCount()) : "beforeIndex out of bounds";

		// Check to see if the TabPanel already contains the Widget. If so,
		// remove it and see if we need to shift the position to the left.
		int idx = getWidgetIndex(child);
		if (idx != -1) {
			remove(child);
			if (idx < beforeIndex) {
				beforeIndex--;
			}
		}

		children.insert(child, beforeIndex);
		tabs.add(beforeIndex, tab);

		tabBar.insert(tab.asWidget(), beforeIndex);

		panel.insert(child, beforeIndex);
		layoutChild(child);

		if (selectedIndex == -1) {
			selectTab(0);
		}
	}

	private void layoutChild(Widget child) {
		panel.setWidgetLeftRight(child, 0, Unit.PX, 0, Unit.PX);
		panel.setWidgetTopBottom(child, barHeight, barUnit, 0, Unit.PX);
		// panel.getWidgetContainerElement(child).getStyle().setDisplay(Display.NONE);
		panel.getWidgetContainerElement(child).getStyle().setVisibility(com.google.gwt.dom.client.Style.Visibility.HIDDEN);
		child.getElement().getStyle().setVisibility(com.google.gwt.dom.client.Style.Visibility.HIDDEN);
		// child.setVisible(false);
	}
}
