package ru.abenefic.spring.shop.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abenefic.spring.shop.auth.model.Role;
import ru.abenefic.spring.shop.auth.model.User;
import ru.abenefic.spring.shop.auth.service.UserService;
import ru.abenefic.spring.shop.core.interfaces.ITokenService;
import ru.abenefic.spring.shop.core.model.UserInfo;
import ru.abenefic.spring.shop.core.model.dtos.AuthRequestDto;
import ru.abenefic.spring.shop.core.model.dtos.AuthResponseDto;
import ru.abenefic.spring.shop.core.model.dtos.SignUpRequestDto;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private ITokenService tokenService;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());
        userService.saveUser(user);
        return "OK";
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());

        UserInfo userInfo = UserInfo.builder()
                .id(user.getId())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .build();
        String token = tokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }


    @GetMapping("/logout")
    public String logout(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Date expiration = tokenService.getTokenExpirationTime(token);
        long redisTimeout = expiration.getTime() - System.currentTimeMillis();
        userService.disableToken(token, redisTimeout);
        return "OK!";
    }

}
