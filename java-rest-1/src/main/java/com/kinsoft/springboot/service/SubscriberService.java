package com.kinsoft.springboot.service;

import java.util.List;

import com.kinsoft.springboot.model.Subscriber;

public interface SubscriberService {

    Subscriber findById(long id);
    Subscriber findByName(String name);
    boolean isSubscriberExist(Subscriber subscriber);
    void updateSubscriber(Subscriber subscriber);
    void saveSubscriber(Subscriber subscriber);
    void deleteSubscriberById(long id);
    List<Subscriber> findAllSubscribers();
    void deleteAllSubscribers();
}
