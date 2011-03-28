package com.calclab.hablar.core.client.validators;

import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

public class TextValidator extends CompositeValidatorChecker {
    private final HasText hasValue;

    public TextValidator(final HasKeyDownHandlers keys, final HasText hasValue, final HasText errorText,
	    final HasState<Boolean> acceptState) {
	super(errorText, acceptState);
	this.hasValue = hasValue;
	keys.addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(final KeyDownEvent event) {
		DeferredCommand.addCommand(getDeferredCommand());
	    }
	});
    }

    public void add(final Validator<String> validator) {
	super.add(new HasValue<String>() {

	    @Override
	    public String getValue() {
		return hasValue.getText();
	    }

	    @Override
	    public void setValue(String value) {
		hasValue.setText(value);
	    }

	    @Override
	    public void setValue(String value, boolean fireEvents) {
		hasValue.setText(value);
	    }

	    @Override
	    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return null;
	    }

	    @Override
	    public void fireEvent(GwtEvent<?> event) {
		// Does nothing
	    }
	}, validator);
    }
}
