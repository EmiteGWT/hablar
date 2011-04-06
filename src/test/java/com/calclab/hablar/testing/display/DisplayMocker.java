package com.calclab.hablar.testing.display;

import org.mockito.Mockito;

public class DisplayMocker {

    private static DisplayStubFactory factory = DisplayStubFactory.instance;

    public static <T> T mock(Class<T> classToMock) {
	T mock = Mockito.mock(classToMock, new ReturnsSingletonMocks(factory));
	return mock;
    }
}
