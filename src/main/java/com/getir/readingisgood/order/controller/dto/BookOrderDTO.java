package com.getir.readingisgood.order.controller.dto;

import com.getir.readingisgood.common.dto.ClientToServerDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookOrderDTO implements ClientToServerDTO {

    @NotNull
    @Min(1)
    private Long bookId;

    @NotNull
    @Min(1)
    private Long numOfBook;

    @Override
    public Object convertToDomainObject() {
        return null;
    }
}
