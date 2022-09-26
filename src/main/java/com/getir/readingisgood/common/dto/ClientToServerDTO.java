package com.getir.readingisgood.common.dto;

public interface ClientToServerDTO<T> {
    T convertToDomainObject();
}
