/**
 * 
 */
package com.calclab.hablar.testing.display;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.gwt.dev.util.collect.HashMap;

public class ReturnsSingletonMocks implements Answer<Object>, Serializable {
    private static final long serialVersionUID = 1465417182645133380L;
    private final HashMap<Method, Object> mocks;
    private final DisplayStubFactory factory;

    public ReturnsSingletonMocks(DisplayStubFactory factory) {
	this.factory = factory;
	mocks = new HashMap<Method, Object>();
    }

    @Override
    public Object answer(InvocationOnMock invocation) throws Throwable {
	return getMock(invocation.getMethod());
    }

    private Object getMock(Method method) {
	Object mock = mocks.get(method);
	Class<?> mockType = method.getReturnType();
	if (mock == null) {
	    mock = factory.create(mockType);
	    mocks.put(method, mock);
	}
	return mock;
    }
}