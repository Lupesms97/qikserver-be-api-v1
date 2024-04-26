package com.v1.qikserve.presentation.exception;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(ExceptionCreatingOrder.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String FailToCreatOrder(ExceptionCreatingOrder ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(OrderNotDeletedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String OrderNotDeleted(OrderNotDeletedException ex) {
        return ex.getMessage();
    }


}
