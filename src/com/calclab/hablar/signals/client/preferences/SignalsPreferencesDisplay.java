package com.calclab.hablar.signals.client.preferences;

import java.util.List;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

public interface SignalsPreferencesDisplay extends Display {

    /**
     * Class used to pass events from the notifier list so that we know
     * which notifier has been changed.
     */
    public class NotifierSelectChange {
	private String id;
	private boolean selected;

	public NotifierSelectChange(final String id, final boolean selected) {
	    this.id = id;
	    this.selected = selected;
	}
	
	public String getId() {
	    return id;
	}
	public boolean isSelected() {
	    return selected;
	}
    }
    
    HasValue<Boolean> getIncomingNotifications();

    HasText getLoading();

    HasValue<Boolean> getRosterNotifications();

    HasValue<Boolean> getTitleSignals();

    void setFormVisible(boolean visible);

    void setLoadingVisible(boolean visible);

    void addNotifier(String id, String name);
    
    void setNotifierSelected(String id, boolean selected);
    
    boolean isNotifierSelected(String id);
    
    List<String> getSelectedNotifiers();
    
    HasValueChangeHandlers<NotifierSelectChange> getNotifiersChangeHandler();
}
