package com.kinsoft.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kinsoft.springboot.model.Subscriber;
import com.kinsoft.springboot.service.SubscriberService;
import com.kinsoft.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api/v1")
public class SubscriberRestController {

    public static final Logger logger = LoggerFactory.getLogger(SubscriberRestController.class);

    @Autowired
    SubscriberService subscriberService; // Service which will do all subscriber data management work

    // Retrieve All Subscribers
    @RequestMapping(value = "/subscriber/", method = RequestMethod.GET)
    public ResponseEntity<List<Subscriber>> listAllSubscribers() {
        List<Subscriber> subscribers = subscriberService.findAllSubscribers();
        if (subscribers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Subscriber>>(subscribers, HttpStatus.OK);
    }

    // Retrieve Single Subscriber
    @RequestMapping(value = "/subscriber/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSubscriber(@PathVariable("id") long id) {
        logger.info("Fetching subscriber with Id {}", id);
        Subscriber subscriber = subscriberService.findById(id);
        if (subscriber == null) {
            logger.error("Subscriber with Id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Subscriber with Id [" + id
                    + "] not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Subscriber>(subscriber, HttpStatus.OK);
    }

    // Create a Subscriber
    @RequestMapping(value = "/subscriber/", method = RequestMethod.POST)
    public ResponseEntity<?> createSubscriber(@RequestBody Subscriber subscriber, UriComponentsBuilder ucBuilder) {
        logger.info("Creating subscriber : {}", subscriber);

        if (subscriberService.isSubscriberExist(subscriber)) {
            logger.error("Unable to create. A subscriber with Name {} already exist", subscriber.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A subscriber with Name [" +
                    subscriber.getName() + "] already exist."), HttpStatus.CONFLICT);
        }
        subscriberService.saveSubscriber(subscriber);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/subscriber/{id}").buildAndExpand(subscriber.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // Update a Subscriber
    @RequestMapping(value = "/subscriber/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSubscriber(@PathVariable("id") long id, @RequestBody Subscriber subscriber) {
        logger.info("Updating subscriber with Id {}", id);

        Subscriber currentSubscriber = subscriberService.findById(id);

        if (currentSubscriber == null) {
            logger.error("Unable to update. Subscriber with Id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Subscriber with Id [" + id + "] not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentSubscriber.setName(subscriber.getName());
        currentSubscriber.setPhone(subscriber.getPhone());
        currentSubscriber.setEmail(subscriber.getEmail());

        subscriberService.updateSubscriber(currentSubscriber);
        return new ResponseEntity<Subscriber>(currentSubscriber, HttpStatus.OK);
    }

    // Delete a Subscriber
    @RequestMapping(value = "/subscriber/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSubscriber(@PathVariable("id") long id) {
        logger.info("Fetching and Deleting Subscriber with id {}", id);

        Subscriber subscriber = subscriberService.findById(id);
        if (subscriber == null) {
            logger.error("Unable to delete. Subscriber with Id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Subscriber with Id [" + id + "] not found."),
                    HttpStatus.NOT_FOUND);
        }
        subscriberService.deleteSubscriberById(id);
        return new ResponseEntity<Subscriber>(HttpStatus.NO_CONTENT);
    }

    // Delete All Subscribers
    @RequestMapping(value = "/subscriber/", method = RequestMethod.DELETE)
    public ResponseEntity<Subscriber> deleteAllSubscribers() {
        logger.info("Deleting All Subscribers");
        subscriberService.deleteAllSubscribers();
        return new ResponseEntity<Subscriber>(HttpStatus.NO_CONTENT);
    }

}
