package com.calclab.hablar.core.client.validators;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Checks a list of validators against the associated value providers.<br/>
 * In the constructor pass the text box that will contain the error message and
 * the component that will change state if validations passes or not.<br>
 * Call the {@link #add(HasValue, Validator)} method for all value
 * provider-validator pair you want to check.<br>
 * Link the validation to events using the {@link #getDeferredCommand()}, that
 * must be executed using {@link DeferredCommand#addCommand(Command)} in an
 * event handler.
 *
 */
public class CompositeValidatorChecker {

    private static class ValueValidatorPair<I> {

	private HasValue<I> hasValue;

	private Validator<I> validator;

	public ValueValidatorPair(HasValue<I> hasValue, Validator<I> validator) {
	    this.hasValue = hasValue;
	    this.validator = validator;
	}

	public boolean isValid() {
	    return validator.isValid(hasValue.getValue());
	}
    }

    private final List<ValueValidatorPair<?>> pairs;
    private final HasText errorText;
    private final HasState<Boolean> acceptEnabled;
    private final Command command;

    public CompositeValidatorChecker(final HasText errorText, final HasState<Boolean> acceptState) {
	this.errorText = errorText;
	acceptEnabled = acceptState;
	pairs = new ArrayList<ValueValidatorPair<?>>();

	command = new Command() {
	    @Override
	    public void execute() {
		validate();
	    }
	};
    }

    public <I> void add(final HasValue<I> hasValue, final Validator<I> validator) {
	pairs.add(new ValueValidatorPair<I>(hasValue, validator));
    }

    public Command getDeferredCommand() {
        return command;
    }

    public void validate() {
	for (final ValueValidatorPair<?> pair : pairs) {
	    if (!pair.isValid()) {
		errorText.setText(pair.validator.getMessage());
		acceptEnabled.setState(false);
		return;
	    }
	}
	errorText.setText("");
	acceptEnabled.setState(true);
    }
}
