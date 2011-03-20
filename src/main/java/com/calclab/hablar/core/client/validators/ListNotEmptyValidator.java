package com.calclab.hablar.core.client.validators;

import java.util.List;


/**
 * Checks if a list is not empty.
 *
 * @param <T> The type of the elements of the list to check.
 */
public class ListNotEmptyValidator<T> implements Validator<List<T>> {

    private String message;

    public ListNotEmptyValidator(String message) {
	this.message = message;
    }

    @Override
    public String getMessage() {
	return message;
    }

    @Override
    public boolean isValid(List<T> value) {
	return !value.isEmpty();
    }
}
