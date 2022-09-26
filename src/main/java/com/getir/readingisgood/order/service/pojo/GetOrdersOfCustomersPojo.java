package com.getir.readingisgood.order.service.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetOrdersOfCustomersPojo {
    private Long customerId;
    private int pageNum;
    private int pageSize;

    public GetOrdersOfCustomersPojo(Long customerId, int pageNum, int pageSize) {
        this.customerId = customerId;
        this.pageNum = pageNum;
        this.pageSize = pageSize != 0 ? pageSize : 30;
    }
}
