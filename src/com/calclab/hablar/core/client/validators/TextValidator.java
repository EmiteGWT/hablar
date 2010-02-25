package com.calclab.hablar.core.client.validators;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HasText;

public class TextValidator {
    private final ArrayList<Validator<String>> validators;
    private final HasText errorText;
    private final HasState<Boolean> acceptEnabled;
    private final HasText hasValue;

    public TextValidator(final HasKeyPressHandlers keys, final HasText hasValue, final HasText errorText,
	    final HasState<Boolean> acceptState) {
	this.hasValue = hasValue;
	this.errorText = errorText;
	acceptEnabled = acceptState;
	validators = new ArrayList<Validator<String>>();

	final Command command = new Command() {
	    @Override
	    public void execute() {
		validate();
	    }
	};
	keys.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(final KeyPressEvent event) {
		DeferredCommand.addCommand(command);
	    }
	});
    }

    public void add(final Validator<String> validator) {
	validators.add(validator);
    }

    public void validate() {
	final String value = hasValue.getText();
	for (final Validator<String> validator : validators) {
	    if (!validator.isValid(value)) {
		errorText.setText(validator.getMessage());
		acceptEnabled.setState(false);
		return;
	    }
	}
	errorText.setText("");
	acceptEnabled.setState(true);
    }

}
