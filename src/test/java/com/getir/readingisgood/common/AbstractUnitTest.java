package com.getir.readingisgood.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.listeners.MockCreationListener;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractUnitTest {
    private final MocksCollector mocksCollector = new MocksCollector();

    @AfterEach
    void afterTest() {
        Mockito.verifyNoInteractions(this.mocksCollector.getMocks());
    }

    private static class MocksCollector {
        private final List<Object> createdMocks = new ArrayList<>();

        public MocksCollector() {
            MockCreationListener mockCreationListener = (o, mockCreationSettings) -> {
                this.createdMocks.add(o);
            };
            ThreadSafeMockingProgress.mockingProgress().clearListeners();
            ThreadSafeMockingProgress.mockingProgress().addListener(mockCreationListener);
        }

        public Object[] getMocks() {
            return this.createdMocks.toArray();
        }
    }
}
