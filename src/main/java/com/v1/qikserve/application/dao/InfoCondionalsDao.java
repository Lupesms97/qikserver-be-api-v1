package com.v1.qikserve.application.dao;


import lombok.Data;

@Data
public class InfoCondionalsDao {
    private boolean hasPromotion;
    private int total ;
    private int totalWithDiscount ;
    private int discount;
}
