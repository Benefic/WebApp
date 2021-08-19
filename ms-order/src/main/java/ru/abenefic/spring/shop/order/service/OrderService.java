package ru.abenefic.spring.shop.order.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.abenefic.spring.shop.core.model.dtos.OrderDto;
import ru.abenefic.spring.shop.order.model.Cart;
import ru.abenefic.spring.shop.order.model.Order;
import ru.abenefic.spring.shop.order.repository.OrdersRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final CartService cartService;
    private final OrdersRepository repository;
    private final ModelMapper modelMapper;

    public OrderService(CartService cartService, OrdersRepository repository, ModelMapper modelMapper) {
        this.cartService = cartService;
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public OrderDto createOrder(UUID cartUuid, long userId, String address) {
        Cart cart = cartService.findCartById(cartUuid);
        Order order = repository.save(new Order(cart, userId));
        order.setAddress(address);
        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto getOrder(long userId, long orderId) {
        Order order = repository.findByUserIdAndId(userId, orderId);
        return modelMapper.map(order, OrderDto.class);
    }

    public List<OrderDto> getOrders(long userId) {
        return null;
    }
}
