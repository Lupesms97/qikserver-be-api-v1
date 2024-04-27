package com.v1.qikserve.application.service.Impl;

import com.v1.qikserve.application.dao.InfoCondionalsDao;
import com.v1.qikserve.application.dto.*;
import com.v1.qikserve.application.service.OrderService;
import com.v1.qikserve.domain.entity.OrderEntity;
import com.v1.qikserve.domain.entity.Products;
import com.v1.qikserve.domain.entity.Promotion;
import com.v1.qikserve.infrastructure.repositoryImpl.OrderRepositoryImpl;
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
    private final GetProductsExtImpl getProductsExt;



    @Transactional
    @Override
    public OrderEntity createOrder(OrderDto orderDto) throws ExceptionCreatingOrder {



        ProductWithPromotionsDto productWithPromotionsDto = getProductsExt.getProductsWithPromotion(orderDto.productId());

        Products products = new FromDto().ToEntity(productWithPromotionsDto);

        InfoCondionalsDao infoCondionalsDao = new InfoCondionalsDao();

        infoCondionalsDao.setHasPromotion(false);
        infoCondionalsDao.setTotal(products.getProducts_price() * orderDto.quantity());
        infoCondionalsDao.setTotalWithDiscount(products.getProducts_price() * orderDto.quantity());
        infoCondionalsDao.setDiscount(0);



        if(!products.getPromotionsApplicable().isEmpty()){
            int bestDiscount = getBestDiscount(products.getPromotionsApplicable(), orderDto.quantity());
            infoCondionalsDao.setHasPromotion(true);
            infoCondionalsDao.setDiscount(bestDiscount);
            infoCondionalsDao.setTotalWithDiscount(
                    bestDiscount -
                    products.getProducts_price() * orderDto.quantity());
        }

        OrderEntity newOrder = new FromDto().ToEntity(orderDto,
                productWithPromotionsDto,
                infoCondionalsDao.getTotal(),
                infoCondionalsDao.isHasPromotion(),
                infoCondionalsDao.getTotalWithDiscount(),
                infoCondionalsDao.getDiscount(),
                getBestPromotion(products.getPromotionsApplicable(), orderDto.quantity()));



            orderRepository.save(newOrder);




        return newOrder;


    }

    @Override
    public List<OrderEntity> createMultipleOrders(List<OrderDto> orderDto) {

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

    private int getBestDiscount(List<Promotion> promotionsApplicable, int quantity) {
        return promotionsApplicable.stream()
                .mapToInt(promotion -> promotion.getDetails().calculateDiscount(quantity))
                .max()
                .orElse(0);
    }

    private Promotion getBestPromotion(List<Promotion> promotionsApplicable, int quantity) {
        return promotionsApplicable.stream()
                .max(Comparator.comparing(promotion -> promotion.getDetails().calculateDiscount(quantity)))
                .orElse(new Promotion());
    }

}
