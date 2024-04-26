package com.v1.qikserve.application.service;

import com.v1.qikserve.application.dto.OrderDto;
import com.v1.qikserve.application.dto.OrderResponseDto;
import com.v1.qikserve.domain.entity.OrderEntity;
import com.v1.qikserve.presentation.exception.ExceptionCreatingOrder;
import com.v1.qikserve.presentation.exception.OrderNotDeletedException;


import java.util.List;


public interface OrderService {

    OrderEntity createOrder(OrderDto orderDto) throws  ExceptionCreatingOrder
    ;

    String cancelOrder(String order) throws OrderNotDeletedException;

    List<OrderResponseDto> getAllOrders();

}
