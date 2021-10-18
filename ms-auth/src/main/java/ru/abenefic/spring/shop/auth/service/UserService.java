package ru.abenefic.spring.shop.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.abenefic.spring.shop.auth.model.Role;
import ru.abenefic.spring.shop.auth.model.User;
import ru.abenefic.spring.shop.auth.repository.RoleRepository;
import ru.abenefic.spring.shop.auth.repository.UserRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public User saveUser(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(List.of(roleUser,roleAdmin));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public void disableToken(String token, long timeout) {
        if (token != null) {
            redisTemplate.opsForValue().set(token, "", timeout, TimeUnit.MILLISECONDS);
        }
    }
}
