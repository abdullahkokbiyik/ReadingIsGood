package com.getir.readingisgood.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractUnitTest {
//    private final MocksCollector mocksCollector = new MocksCollector();
//
//    @AfterEach
//    void afterTest() {
//        Mockito.verifyNoInteractions(this.mocksCollector.getMocks());
//    }
//
//    private static class MocksCollector {
//        private final List<Object> createdMocks = new ArrayList<>();
//
//        public MocksCollector() {
//            MockCreationListener mockCreationListener = (o, mockCreationSettings) -> {
//                this.createdMocks.add(o);
//            };
//            ThreadSafeMockingProgress.mockingProgress().clearListeners();
//            ThreadSafeMockingProgress.mockingProgress().addListener(mockCreationListener);
//        }
//
//        public Object[] getMocks() {
//            return this.createdMocks.toArray();
//        }
//    }

    @BeforeEach
    public void beforeTest() throws IllegalAccessException {
        Mockito.reset(getMockList());
    }

    @AfterEach
    public void afterTest() throws IllegalAccessException {
        Object[] mockObjectArray = getMockList();
        if (mockObjectArray.length != 0) {
            Mockito.verifyNoMoreInteractions(mockObjectArray);
        }
    }

    protected Object[] getMockList() throws IllegalAccessException {
        List<Object> mockObjectList = new ArrayList<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Mock.class) && MockUtil.isMock(field.get(this))) {
                mockObjectList.add(field.get(this));
            }
        }
        return mockObjectList.toArray();
    }
}
