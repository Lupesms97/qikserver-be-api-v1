package com.v1.qikserve.application.service;

import com.v1.qikserve.application.dto.OrderDto;
import com.v1.qikserve.application.dto.OrderResponseDto;
import com.v1.qikserve.application.dto.ResponseDto;
import com.v1.qikserve.presentation.exception.ExceptionCreatingOrder;
import com.v1.qikserve.presentation.exception.OrderNotDeletedException;


import java.util.List;


public interface OrderService {

    ResponseDto createOrder(OrderDto orderDto) throws  ExceptionCreatingOrder;
    List<ResponseDto> createMultipleOrders(List<OrderDto> orderDto) throws ExceptionCreatingOrder;
    String cancelOrder(String order) throws OrderNotDeletedException;
    List<OrderResponseDto> getAllOrders();

}
