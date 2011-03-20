package com.calclab.hablar.core.client.util;

import com.google.gwt.user.client.Command;

/**
 * Interface to be implemented by classes which run a queued set of commands.
 * 
 * @author Ash
 * 
 */
public interface CommandQueue {
    /**
     * Adds a command to be queued
     * 
     * @param command
     */
    void addCommand(final Command command);

    /**
     * Adds a high priority command to be queued. These will be run before all
     * the normal commands (added using {@link #addCommand(Command)} are run).
     * 
     * @param command
     */
    void addPriorityCommand(final Command command);
}
