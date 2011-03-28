package com.calclab.hablar.core.client.util;

import com.google.gwt.user.client.Command;

/**
 * Very simple implementation of {@link CommandQueue} which runs the commands
 * immediately.
 */
public class ImmediateRunCommandScheduler implements CommandQueue {

    @Override
    public void addCommand(final Command command) {
	command.execute();
    }

    @Override
    public void addPriorityCommand(final Command command) {
	command.execute();
    }
}
