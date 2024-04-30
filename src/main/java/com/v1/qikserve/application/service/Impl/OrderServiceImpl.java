package com.v1.qikserve.application.service.Impl;

import com.v1.qikserve.application.dao.InfoCondionalsDao;
import com.v1.qikserve.application.dto.*;
import com.v1.qikserve.application.enums.TypePromotion;
import com.v1.qikserve.application.service.OrderService;
import com.v1.qikserve.domain.entity.OrderEntity;
import com.v1.qikserve.domain.entity.Products;
import com.v1.qikserve.domain.entity.Promotion;
import com.v1.qikserve.domain.entity.promotionDetails.PromotionDetails;
import com.v1.qikserve.infrastructure.repositoryImpl.OrderRepositoryImpl;
import com.v1.qikserve.infrastructure.repositoryImpl.PromotionRepositoryImpl;
import com.v1.qikserve.infrastructure.repositoryImpl.promotionDetails.PromotionDetailsRepositoryImpl;
import com.v1.qikserve.infrastructure.repositoryImpl.promotionDetails.PromotionDetailsRepositoryInstace;
import com.v1.qikserve.presentation.exception.ExceptionCreatingOrder;
import com.v1.qikserve.presentation.exception.OrderNotDeletedException;
import com.v1.qikserve.presentation.mapper.FromDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryImpl orderRepository;
    private final PromotionRepositoryImpl promotionRepository;
    private final GetProductsExtImpl getProductsExt;
    private final PromotionDetailsRepositoryInstace promotionDetailsRepositoryInstace;



    @Transactional
    @Override
    public ResponseDto createOrder(OrderDto orderDto) throws ExceptionCreatingOrder {


        ProductWithPromotionsDto productWithPromotionsDto = getProductsExt.getProductsWithPromotion(orderDto.productId());

        Products products = new FromDto().ToEntity(productWithPromotionsDto);

        InfoCondionalsDao infoCondionalsDao = getInfoCondionalsDao(products, orderDto);

        OrderEntity order = prepareAndStoreOrderPromotionAndDetails(orderDto, productWithPromotionsDto, infoCondionalsDao, products);

        orderRepository.save(order);

        return createResponse(order);



    }

    @Override
    public List<ResponseDto> createMultipleOrders(List<OrderDto> orderDto) {

        return orderDto.stream().map(order -> {
                    try {
                        return createOrder(order);
                    } catch (ExceptionCreatingOrder exceptionCreatingOrder) {
                        exceptionCreatingOrder.printStackTrace();
                        return null;
                    }
                }).filter(Objects::nonNull)
                .toList();
    }

    @Override
    public String cancelOrder(String idOrderToCancel) throws OrderNotDeletedException {
        orderRepository.findById(UUID.fromString(idOrderToCancel)).ifPresent(orderRepository::delete);
        return idOrderToCancel;
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
//        return orderRepository.findAll().stream().map(order -> new FromEntity().ToDto(order)).toList();
        return null;
    }


    private Promotion getBestPromotion(Products products, int quantity) {
        return products.getPromotionsApplicable().stream()
                .max(Comparator.comparing(promotion -> promotion.getDetails().calculateDiscount(quantity, products.getProducts_price())))
                .orElse(new Promotion());
    }

    private InfoCondionalsDao getInfoCondionalsDao(Products products, OrderDto orderDto) {

        InfoCondionalsDao infoCondionalsDao = new InfoCondionalsDao();

        infoCondionalsDao.setHasPromotion(false);
        infoCondionalsDao.setTotal(products.getProducts_price() * orderDto.quantity());
        infoCondionalsDao.setTotalWithDiscount(products.getProducts_price() * orderDto.quantity());
        infoCondionalsDao.setDiscount(0);



        if(!products.getPromotionsApplicable().isEmpty()){
            Promotion promotion = getBestPromotion(products, orderDto.quantity());
            int discount_info = promotion.getDetails().calculateDiscount(orderDto.quantity(), products.getProducts_price());
            infoCondionalsDao.setHasPromotion(true);
            infoCondionalsDao.setDiscount(discount_info);
            infoCondionalsDao.setTotalWithDiscount(
                    getTotalWithDiscountTypeBased(products,
                            TypePromotion.valueOf(promotion.getType()),
                            orderDto.quantity()
                            , discount_info
                            , promotion.getDetails().getPrice()));
        }

        return infoCondionalsDao;
    }

    private OrderEntity creatNewOrder(
            OrderDto orderDto,
            ProductWithPromotionsDto productWithPromotionsDto,
            InfoCondionalsDao infoCondionalsDao,
            Products products){

        return new FromDto().ToEntity(orderDto,
                productWithPromotionsDto,
                infoCondionalsDao.getTotal(),
                infoCondionalsDao.isHasPromotion(),
                getBestPromotion(products, orderDto.quantity()),
                infoCondionalsDao.getTotalWithDiscount(),
                infoCondionalsDao.getDiscount()
                );
    }



    private OrderEntity prepareAndStoreOrderPromotionAndDetails(
            OrderDto orderDto,
            ProductWithPromotionsDto productWithPromotionsDto,
            InfoCondionalsDao infoCondionalsDao,
            Products products){


        OrderEntity order = creatNewOrder(orderDto, productWithPromotionsDto, infoCondionalsDao, products);


        PromotionDetailsRepositoryImpl promotionsDetails = promotionDetailsRepositoryInstace.getRepository(order.getPromotionApplied().getType());
        PromotionDetails promotionDetailsToSave = promotionsDetails.save(order.getPromotionApplied().getDetails());

        Promotion promotionToSave = promotionRepository.save(order.getPromotionApplied());

        order.getPromotionApplied().setDetails(promotionDetailsToSave);
        order.setPromotionApplied(promotionToSave);

        return order;

    }

    private ResponseDto createResponse(OrderEntity order) {
        ResponseDto response = new ResponseDto(
                order.getId().toString(),
                201,
                true,
                "NO_PROMOTION",
                order.getDiscount(),
                order.getTotal(),
                order.getTotalWithDiscount()
        );


        if ("BUY_X_GET_Y_FREE".equals(order.getPromotionApplied().getType())&& order.isHasPromotion()) {
            return new ResponseDto(
                    order.getId().toString(),
                    201,
                    true,
                    order.getPromotionApplied().getType(),
                    order.getTotalWithDiscount(),
                    order.getTotal(),
                    order.getTotal()

            );




        }

        if ("QTY_BASED_PRICE_OVERRIDE".equals(order.getPromotionApplied().getType())&& order.isHasPromotion()) {
            return new ResponseDto(
                    order.getId().toString(),
                    201,
                    true,
                    order.getPromotionApplied().getType(),
                    (order.getTotal() - order.getTotalWithDiscount()),
                    order.getTotal(),
                    order.getTotalWithDiscount()
            );


        }

        if ("FLAT_PERCENT".equals(order.getPromotionApplied().getType())&& order.isHasPromotion()) {

            return new ResponseDto(
                    order.getId().toString(),
                    201,
                    true,
                    order.getPromotionApplied().getType(),
                    (order.getTotal() - order.getTotalWithDiscount()),
                    order.getTotal(),
                    order.getTotalWithDiscount());

        }

        if("NO_PROMOTION".equals(order.getPromotionApplied().getType())&& order.isHasPromotion()){
            return new ResponseDto(
                    order.getId().toString(),
                    201,
                    true,
                    order.getPromotionApplied().getType(),
                    0,
                    order.getTotal(),
                    order.getTotalWithDiscount()
            );
        }

        return response;
    }
    private int getTotalWithDiscountTypeBased(Products product,
                                              TypePromotion typePromotion,
                                              int quantity, int discount,
                                              int discountPrice) {
        int totalWithDiscount = 0;
        int total = product.getProducts_price() * quantity;

        switch (typePromotion.toString()) {
            case "BUY_X_GET_Y_FREE":
                totalWithDiscount = discount * product.getProducts_price();
                break;
            case "QTY_BASED_PRICE_OVERRIDE":
                int totalNormalPrice = (quantity - (discount*2) ) * product.getProducts_price();
                int totalDiscountPrice = ((discount) *discountPrice);
                totalWithDiscount = totalNormalPrice + totalDiscountPrice;
                break;
            case "FLAT_PERCENT":
                totalWithDiscount = total - (total * discount / 100);
                break;
            case "NO_PROMOTION":
                totalWithDiscount = total;
                break;
            default:
                break;
        }
        return totalWithDiscount;

    }





}
