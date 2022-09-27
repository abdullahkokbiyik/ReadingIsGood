package com.getir.readingisgood.common.context;

import org.springframework.stereotype.Service;

@Service
public class MessageContext {
    public void addErrorMessage(String message, Object... params) {
        throw new IllegalArgumentException(String.format(message, params));
    }
}
