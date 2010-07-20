package com.calclab.hablar.signals.client.browserfocus;

import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.browser.BrowserFocusHandler;
import com.calclab.hablar.core.client.browser.BrowserFocusHandler.BrowserFocusListener;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedHandler;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.signals.client.unattended.UnattendedPagesManager;

/**
 * This class is a workaround to clear the focus on the active chat page FIXME:
 * workaround to clear the focus <br/>
 * 
 * <br/>
 * TODO: change the page/header visibility system... quite a big job
 * 
 * 
 */
public class BrowserFocusManager {

    protected ChatPresenter currentFocused;

    public BrowserFocusManager(HablarEventBus eventBus, final UnattendedPagesManager unattendedManager,
	    BrowserFocusHandler handler) {

	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		if (event.getVisibility() == Visibility.focused) {
		    Page<?> page = event.getPage();
		    if (PairChatPresenter.TYPE.equals(page.getType()) || RoomPresenter.TYPE.equals(page.getType())) {
			currentFocused = (ChatPresenter) page;
		    }
		}
	    }
	});

	handler.setFocusListener(new BrowserFocusListener() {
	    @Override
	    public void onBrowserFocusChanged(boolean hasFocus) {
		if (currentFocused != null) {
		    if (hasFocus == false) {
			currentFocused.getDisplay().setTextBoxFocus(false);
		    } else if (!unattendedManager.contains(currentFocused)) {
			currentFocused.getDisplay().setTextBoxFocus(true);
		    }
		}
	    }
	});
    }
}
