package ru.abenefic.spring.shop.core.configuration;

public interface CachedDataChangeSubscriber<T extends Object> {
    void update(CachedDataChangePublisher source, T  object);
}
