package com.v1.qikserve.presentation.controller;

import com.v1.qikserve.application.dto.OrderDto;
import com.v1.qikserve.application.dto.OrderResponseDto;
import com.v1.qikserve.application.dto.ResponseDto;
import com.v1.qikserve.application.dto.ResponseDtoWithListOfOrders;
import com.v1.qikserve.application.service.Impl.OrderServiceImpl;
import com.v1.qikserve.presentation.exception.ExceptionCreatingOrder;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ResponseDto.class)
                    ) }),
            @ApiResponse(responseCode = "500", description = "Could not creat the order", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ExceptionCreatingOrder.class)) }) })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody OrderDto orderDto) {
        ResponseDto responseDto = orderService.createOrder(orderDto);


        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Orders created successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDto.class, type = "array")
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Could not create the orders",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionCreatingOrder.class)
                            )
                    }
            )})
    @PostMapping("/create/multiples")
    public ResponseEntity<List<ResponseDto>> createOrders(@RequestBody List<OrderDto> orderDto) {
        List<ResponseDto> response = orderService.createMultipleOrders(orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order delete successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ResponseDto.class)
                    ) }),
            @ApiResponse(responseCode = "500", description = "Could not delete the order:", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ExceptionCreatingOrder.class)) }) })
    @DeleteMapping("/cancel/{order_id}")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable String order_id) {
        String response = orderService.cancelOrder(order_id);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
                "Order canceled successfully",
                200,
                true,
                "No promotion applied",
                0,
                0,
                0
        ));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order delete successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ResponseDtoWithListOfOrders.class)
                    ) }),
            @ApiResponse(responseCode = "204", description = "Could not find any order", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ResponseDtoWithListOfOrders.class)) }) })
    @GetMapping("/allOrdes")
    public ResponseEntity<ResponseDtoWithListOfOrders> getAllOrders() {
        List<OrderResponseDto> response = orderService.getAllOrders();

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoWithListOfOrders(
                    response,
                    "No orders found",
                    204,
                    false
            ));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDtoWithListOfOrders(
                orderService.getAllOrders(),
                "All orders",
                200,
                true
        ));
    }
}
