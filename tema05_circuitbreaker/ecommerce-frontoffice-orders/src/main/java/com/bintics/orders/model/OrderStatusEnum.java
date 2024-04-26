package com.bintics.orders.model;

import lombok.ToString;

@ToString
public enum OrderStatusEnum {

    OPEN(0),
    CLOSE(1);

    private final int status;

    OrderStatusEnum(int status) {
        this.status = status;
    }

    public static OrderStatusEnum getEnumFrom(Integer status) {
        if (status == null) {
            throw new RuntimeException("status no valid");
        }
        for (var v : OrderStatusEnum.values()) {
            if (v.status == status) {
                return v;
            }
        }
        return null;
    }

    public int getValue() {
        return this.status;
    }

}
