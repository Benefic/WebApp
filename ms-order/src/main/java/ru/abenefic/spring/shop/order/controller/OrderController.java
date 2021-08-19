package ru.abenefic.spring.shop.order.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.abenefic.spring.shop.core.interfaces.ITokenService;
import ru.abenefic.spring.shop.core.model.UserInfo;
import ru.abenefic.spring.shop.core.model.dtos.OrderDto;
import ru.abenefic.spring.shop.order.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;
    private final ITokenService tokenService;

    public OrderController(OrderService service, ITokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }


    @PostMapping
    public OrderDto createOrder(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                @RequestParam UUID cartUuid,
                                @RequestParam String address) {
        UserInfo userInfo = tokenService.parseToken(token.replace("Bearer ", ""));
        return service.createOrder(cartUuid, userInfo.getId(), address);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                             @PathVariable long id) {
        UserInfo userInfo = tokenService.parseToken(token.replace("Bearer ", ""));
        return service.getOrder(userInfo.getId(), id);
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
        UserInfo userInfo = tokenService.parseToken(token.replace("Bearer ", ""));
        return service.getOrders(userInfo.getId());
    }

}
