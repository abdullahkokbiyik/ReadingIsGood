package com.getir.readingisgood.order.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrdersOfCustomerPojo {
    private Long customerId;
    private int pageNum;
    private int pageSize;
}
