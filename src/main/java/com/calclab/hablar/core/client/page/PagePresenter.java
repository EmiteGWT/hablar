package com.calclab.hablar.core.client.page;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;

public class PagePresenter<T extends Display> implements Page<T> {

	/**
	 * The current visibility of the page in a container
	 */
	public static enum Visibility {
		/**
		 * Set this visibility to focus the page (set the page header and
		 * content visibles)
		 */
		focused,
		/**
		 * Set this visibility to show the page header but not the content
		 */
		notFocused,
		/**
		 * Set this visibility to close the page (the page header and content
		 * will not visibile)
		 */
		hidden,
		/**
		 * Set this visibility if you want to close the page if its open or open
		 * if its closed
		 */
		toggle
	}

	private static int index = 0;
	protected final T display;
	protected final HablarEventBus eventBus;
	private final String pageType;
	private final String id;

	protected final PageState model;

	public PagePresenter(final String pageType, final HablarEventBus eventBus, final T display) {
		this(pageType, "" + ++index, eventBus, display);
	}

	public PagePresenter(final String pageType, final String id, final HablarEventBus eventBus, final T display) {
		this.pageType = pageType;
		this.eventBus = eventBus;
		this.display = display;
		this.id = pageType + "-" + id;
		model = new PageState(eventBus, this);
	}

	@Override
	public T getDisplay() {
		return display;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public PageState getState() {
		return model;
	}

	@Override
	public String getType() {
		return pageType;
	}

	@Override
	public Visibility getVisibility() {
		return model.getVisibility();
	}

	@Override
	public void requestVisibility(final Visibility newVisibility) {
		eventBus.fireEvent(new VisibilityChangeRequestEvent(this, newVisibility));
	}

	@Override
	public void setVisibility(final Visibility visibility) {
		if (visibility == Visibility.focused) {
			onBeforeFocus();
		}
		model.setVisibility(visibility);
	}

	/**
	 * Override this method
	 */
	protected void onBeforeFocus() {

	}

}
