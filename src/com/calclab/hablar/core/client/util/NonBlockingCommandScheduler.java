package com.calclab.hablar.core.client.util;

import java.util.LinkedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.user.client.Command;

/**
 * Very simple class to implement a queue of commands which will be run as an
 * incremental command.
 * 
 * This is similar to using the normal GWT scheduler with deferred commands,
 * except that in a deferred command the execution of the command will be
 * deferred, but all the deferred commands will be executed in one pass. This
 * class, on the other hand, will execute each command separately as part of an
 * incremental command, allowing the browser's event loop to run between
 * commands as appropriate.
 * 
 * Note that there is no consideration of synchronisation here as JS is single
 * threaded.
 */
public class NonBlockingCommandScheduler implements RepeatingCommand {
    private final LinkedList<Command> commandQueue;
    private final LinkedList<Command> priorityCommandQueue;
    private boolean isExecuting;

    public NonBlockingCommandScheduler() {
	commandQueue = new LinkedList<Command>();
	priorityCommandQueue = new LinkedList<Command>();
	isExecuting = false;
    }

    /**
     * Adds a command to be queued
     * 
     * @param command
     */
    public void addCommand(final Command command) {
	commandQueue.addLast(command);

	GWT.log(this.getClass().getName() + " - Command added: " + command);

	this.startIfRequired();
    }

    /**
     * Adds a high priority command to be queued. These will be run before all
     * the normal commands (added using {@link #addCommand(Command)} are run).
     * 
     * @param command
     */
    public void addPriorityCommand(final Command command) {
	priorityCommandQueue.addLast(command);

	GWT.log(this.getClass().getName() + " - Priority command added: " + command);

	this.startIfRequired();
    }

    @Override
    public boolean execute() {
	final Command command;

	if (!priorityCommandQueue.isEmpty()) {
	    command = priorityCommandQueue.remove();
	} else if (!commandQueue.isEmpty()) {
	    command = commandQueue.remove();
	} else {
	    // This shouldn't really happen but we might as well handle it
	    // anyway
	    isExecuting = false;
	    return false;
	}

	command.execute();

	isExecuting = ((commandQueue.size() > 0) || (priorityCommandQueue.size() > 0));

	return isExecuting;
    }

    private void startIfRequired() {
	if (!isExecuting) {
	    isExecuting = true;
	    Scheduler.get().scheduleIncremental(NonBlockingCommandScheduler.this);
	}
    }
}
