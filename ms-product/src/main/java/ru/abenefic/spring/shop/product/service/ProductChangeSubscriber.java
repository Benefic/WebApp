package ru.abenefic.spring.shop.product.service;

import org.springframework.data.redis.core.RedisTemplate;
import ru.abenefic.spring.shop.core.configuration.CachedDataChangePublisher;
import ru.abenefic.spring.shop.core.configuration.CachedDataChangeSubscriber;

public class ProductChangeSubscriber implements CachedDataChangeSubscriber<Long> {

    private final RedisTemplate<String, String> redisTemplate;

    public ProductChangeSubscriber(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void update(CachedDataChangePublisher source, Long id) {
        redisTemplate.delete(ProductService.PRODUCT_KEY_PREFIX + id);
    }
}
