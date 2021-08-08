package ru.abenefic.spring.shop.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.abenefic.spring.shop.core.interfaces.ITokenService;
import ru.abenefic.spring.shop.core.model.UserInfo;
import ru.abenefic.spring.shop.core.model.dtos.CartDto;
import ru.abenefic.spring.shop.order.service.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {


}
