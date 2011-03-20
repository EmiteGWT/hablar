package com.calclab.hablar.core.client.validators;

public interface Validator<T> {

    String getMessage();

    boolean isValid(T value);

}
