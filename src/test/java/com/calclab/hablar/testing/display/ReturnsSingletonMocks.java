/**
 * 
 */
package com.calclab.hablar.testing.display;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.dev.util.collect.HashMap;

public class ReturnsSingletonMocks implements Answer<Object>, Serializable {
    private static final long serialVersionUID = 1465417182645133380L;
    private final HashMap<Method, Object> mocks;
    private final DisplayStubFactory factory;

    public ReturnsSingletonMocks(final DisplayStubFactory factory) {
	this.factory = factory;
	mocks = new HashMap<Method, Object>();
    }

    @Override
    public Object answer(final InvocationOnMock invocation) throws Throwable {
	return getMock(invocation.getMethod());
    }

    public <T> T mock(final Class<T> classToMock) {
	final T mock = Mockito.mock(classToMock, this);
	return mock;
    }

    private Object getMock(final Method method) {
	Object mock = mocks.get(method);
	if (mock == null) {
	    final Class<?> mockType = method.getReturnType();
	    if (isDisplay(mockType)) {
		mock = mock(mockType);
	    } else {
		mock = factory.create(mockType);
	    }
	    mocks.put(method, mock);
	}
	return mock;
    }

    private boolean isDisplay(final Class<?> mockType) {
	return Display.class.isAssignableFrom(mockType);
    }
}