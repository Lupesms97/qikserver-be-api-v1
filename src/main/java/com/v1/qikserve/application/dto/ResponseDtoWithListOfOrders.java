package com.v1.qikserve.application.dto;

import java.util.List;

public record ResponseDtoWithListOfOrders(

        List<OrderResponseDto> orders,
        String message,
        int status,
        boolean success

) {
}
