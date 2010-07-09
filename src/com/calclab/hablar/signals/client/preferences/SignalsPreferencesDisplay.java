package com.calclab.hablar.signals.client.preferences;

import java.util.List;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Interface for classes which can provide a display for the signals preferences
 */
public interface SignalsPreferencesDisplay extends Display {
    
    /**
     * Returns the control for the "incoming notifications"
     * @return the {@link HasValue} representing the control
     */
    HasValue<Boolean> getIncomingNotifications();

    /**
     * Returns the "loading" label
     * @return the {@link HasText} representing the label
     */
    HasText getLoading();
    
    /**
     * Returns the control for the "roster notifications"
     * @return the {@link HasValue} representing the control
     */
    HasValue<Boolean> getRosterNotifications();
    
    /**
     * Returns the control for the "incoming notifications"
     * @return the {@link HasValue} representing the control
     */
    HasValue<Boolean> getTitleSignals();

    /**
     * Sets whether the form is visible
     * @param visible
     */
    void setFormVisible(boolean visible);

    /**
     * Sets whether the loading notification is visible
     * @param visible
     */
    void setLoadingVisible(boolean visible);

    /**
     * Adds a notifier to the selectable notifiers
     * @param id the notifier id
     * @param name the notifier display name
     */
    void addNotifier(String id, String name);
    
    /**
     * Sets whether a notifier checkbox is checked
     * @param id the notifier id
     * @param selected <code>true</code>
     */
    void setNotifierSelected(String id, boolean selected);
    
    /**
     * Returns whether a notifier checkbox is selected
     * @param id the notifier id
     * @return <code>true</code> if the notifier is selected
     */
    boolean isNotifierSelected(String id);
    
    /**
     * Returns a list of the selected notifier ids
     * @return the list of notifier ids
     */
    List<String> getSelectedNotifiers();
}
