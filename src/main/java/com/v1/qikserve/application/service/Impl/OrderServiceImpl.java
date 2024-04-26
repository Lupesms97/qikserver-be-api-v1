package com.v1.qikserve.application.service.Impl;

import com.v1.qikserve.application.dao.InfoCondionalsDao;
import com.v1.qikserve.application.dto.*;
import com.v1.qikserve.application.service.OrderService;
import com.v1.qikserve.domain.entity.OrderEntity;
import com.v1.qikserve.infrastructure.repositoryImpl.OrderRepositoryImpl;
import com.v1.qikserve.presentation.exception.ExceptionCreatingOrder;
import com.v1.qikserve.presentation.exception.OrderNotDeletedException;
import com.v1.qikserve.presentation.mapper.FromDto;
import com.v1.qikserve.presentation.mapper.FromEntity;
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

        InfoCondionalsDao infoCondionalsDao = new InfoCondionalsDao();

        infoCondionalsDao.setHasPromotion(false);
        infoCondionalsDao.setTotal(productWithPromotionsDto.price() * orderDto.quantity());
        infoCondionalsDao.setTotalWithDiscount(productWithPromotionsDto.price() * orderDto.quantity());
        infoCondionalsDao.setDiscount(0);


        PromotionDto bestPromotion = findBestPromotion(productWithPromotionsDto, orderDto.quantity());

        if(!bestPromotion.id().equals("")) {
            infoCondionalsDao.setHasPromotion(true);
            infoCondionalsDao.setTotal(productWithPromotionsDto.price() * orderDto.quantity());
            infoCondionalsDao.setTotalWithDiscount(SetTotalWithDiscount(orderDto.quantity(), bestPromotion));
            infoCondionalsDao.setDiscount(infoCondionalsDao.getTotal() - infoCondionalsDao.getTotalWithDiscount());
        }

        OrderEntity newOrder = new FromDto().ToEntity(orderDto,
                productWithPromotionsDto,
                infoCondionalsDao.getTotal(),
                infoCondionalsDao.isHasPromotion(),
                infoCondionalsDao.getTotalWithDiscount(),
                infoCondionalsDao.getDiscount(),
                bestPromotion);


        try {
            orderRepository.save(newOrder);
        } catch (Exception e) {
            throw new ExceptionCreatingOrder("Error saving the order");
        }



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
        return orderRepository.findAll().stream().map(order -> new FromEntity().ToDto(order)).toList();
    }

    private int SetTotalWithDiscount(int quantity, PromotionDto promotion) {
        return promotion.price() * (quantity / promotion.required_qty()) +
                (quantity % promotion.required_qty()) * promotion.price();

    }

    private PromotionDto findBestPromotion(ProductWithPromotionsDto productWithPromotionsDto, int quantity) {

        List<PromotionDto> availablePromotions = filterApplicablePromotions(productWithPromotionsDto, quantity);

        Optional<PromotionDto> bestPromotion = availablePromotions.stream()
                .max(Comparator.comparing(PromotionDto::price));

        return bestPromotion.orElse(new PromotionDto("","",0,0));
    }

    private List<PromotionDto> filterApplicablePromotions(ProductWithPromotionsDto productWithPromotionsDto, int quantity) {
        return productWithPromotionsDto.promotions().stream()
                .filter(promotionDto -> promotionDto.required_qty() <= quantity)
                .toList();
    }
}
