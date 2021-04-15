package ru.abenefic.spring.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.abenefic.spring.model.entities.User;
import ru.abenefic.spring.services.UserService;

@Component
public class ShopUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public ShopUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ShopUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByLogin(username);
        return ShopUserDetails.fromUserEntity(user);
    }
}
