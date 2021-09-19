package ru.abenefic.spring.shop.core.interfaces;

import io.jsonwebtoken.ExpiredJwtException;
import ru.abenefic.spring.shop.core.model.UserInfo;

import java.util.Date;

public interface ITokenService {
    String generateToken(UserInfo user);

    UserInfo parseToken(String token) throws ExpiredJwtException;

    Date getTokenExpirationTime(String token);

}
