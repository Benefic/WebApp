package ru.abenefic.spring.shop.core.configuration;

import java.util.ArrayList;
import java.util.List;

public abstract class CachedDataChangePublisher {
    private final List<CachedDataChangeSubscriber> subscribers = new ArrayList<>();

    // подключить Наблюдателя
    public void subscribe(CachedDataChangeSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    // отключить Наблюдателя
    public void unsubscribe(CachedDataChangeSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    // проинформировать подписантов об изменениях
    protected void notify(Object arg){
        for (CachedDataChangeSubscriber subscriber : subscribers) {
            subscriber.update(this, arg);
        }
    }
}
