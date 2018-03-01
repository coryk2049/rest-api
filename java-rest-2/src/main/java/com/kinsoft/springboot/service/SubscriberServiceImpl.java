package com.kinsoft.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import com.kinsoft.springboot.model.Subscriber;

@Service("subscriberService")
public class SubscriberServiceImpl implements SubscriberService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Subscriber> subscribers;

    static {
        subscribers = populateDummySubscribers();
    }

    public List<Subscriber> findAllSubscribers() {
        return subscribers;
    }

    public Subscriber findById(long id) {
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getId() == id) {
                return subscriber;
            }
        }
        return null;
    }

    public Subscriber findByName(String name) {
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getName().equalsIgnoreCase(name)) {
                return subscriber;
            }
        }
        return null;
    }

    public void saveSubscriber(Subscriber subscriber) {
        subscriber.setId(counter.incrementAndGet());
        subscribers.add(subscriber);
    }

    public void updateSubscriber(Subscriber subscriber) {
        int index = subscribers.indexOf(subscriber);
        subscribers.set(index, subscriber);
    }

    public void deleteSubscriberById(long id) {
        for (Iterator<Subscriber> iterator = subscribers.iterator(); iterator.hasNext(); ) {
            Subscriber subscriber = iterator.next();
            if (subscriber.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isSubscriberExist(Subscriber subscriber) {
        return findByName(subscriber.getName()) != null;
    }

    public void deleteAllSubscribers() {
        subscribers.clear();
    }

    private static List<Subscriber> populateDummySubscribers() {
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        subscribers.add(new Subscriber(counter.incrementAndGet(), "Taylor Swift", "5716436001", "taylor.swift@example.com"));
        subscribers.add(new Subscriber(counter.incrementAndGet(), "Clare Swift", "5716436002", "clare.swift@example.com"));
        subscribers.add(new Subscriber(counter.incrementAndGet(), "Rachel Swift", "5716436003", "rachel.swift@example.com"));
        subscribers.add(new Subscriber(counter.incrementAndGet(), "Zoey Swift", "5716436004", "zoey.swift@example.com"));
        return subscribers;
    }

}
