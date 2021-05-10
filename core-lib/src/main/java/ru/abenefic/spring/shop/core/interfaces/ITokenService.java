package ru.abenefic.spring.shop.core.interfaces;

import io.jsonwebtoken.ExpiredJwtException;
import ru.abenefic.spring.shop.core.model.UserInfo;

public interface ITokenService {
    String generateToken(UserInfo user);

    UserInfo parseToken(String token) throws ExpiredJwtException;
}
