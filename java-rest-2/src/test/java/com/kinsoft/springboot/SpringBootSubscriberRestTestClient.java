package com.kinsoft.springboot;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import com.kinsoft.springboot.model.Subscriber;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpringBootSubscriberRestTestClient {

    private static final String REST_SERVICE_URI = "http://localhost:9090/SpringBootSubscriberRest/api/v1";

    // GET
    @SuppressWarnings("unchecked")
    private static void listAllSubscribers() {
        System.out.println("Testing listAllSubscribers API...");
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> subscribersMap = restTemplate.getForObject(REST_SERVICE_URI + "/subscriber/", List.class);
        if (subscribersMap != null) {
            for (LinkedHashMap<String, Object> map : subscribersMap) {
                System.out.println("Subscriber: Id=" + map.get("id") + ", Name=" + map.get("name") + ", Phone=" + map.get("phone") + ", Email=" + map.get("email"));
            }
        } else {
            System.out.println("No subscriber exist...");
        }
    }

    // DELETE
    private static void deleteAllSubscribers() {
        System.out.println("Testing all deleteAllSubscribers API...");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/subscriber/");
    }

    // GET
    private static void getHealth() {
        System.out.println("Testing health API...");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(REST_SERVICE_URI + "/../health", String.class);
        //ResponseEntity<String> response = restTemplate.getForEntity(REST_SERVICE_URI + "/../health", String.class);
        System.out.println(response);
    }

    // GET
    private static void getSubscriber() {
        System.out.println("Testing getSubscriber API...");
        RestTemplate restTemplate = new RestTemplate();
        Subscriber subscriber = restTemplate.getForObject(REST_SERVICE_URI + "/subscriber/1", Subscriber.class);
        System.out.println(subscriber);
    }

    // POST
    private static void createSubscriber() {
        System.out.println("Testing createSubscriber API...");
        RestTemplate restTemplate = new RestTemplate();
        Subscriber subscriber = new Subscriber(5, "Robert Smith", "5716436005", "robert.smith@example.com");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/subscriber/", subscriber, Subscriber.class);
        System.out.println("Location: "+uri.toASCIIString());
        //RestTemplate restTemplate = new RestTemplate();
        subscriber = new Subscriber(6, "David Smith", "5716436006", "david.smith@example.com");
        uri = restTemplate.postForLocation(REST_SERVICE_URI+"/subscriber/", subscriber, Subscriber.class);
        System.out.println("Location: "+uri.toASCIIString());
    }

    // PUT
    private static void updateSubscriber() {
        System.out.println("Testing updateSubscriber API...");
        RestTemplate restTemplate = new RestTemplate();
        Subscriber subscriber = new Subscriber(5, "Audrey Smith", "5716436022", "audrey.smith@example.com");
        restTemplate.put(REST_SERVICE_URI + "/subscriber/5", subscriber);
        System.out.println(subscriber);
    }

    // DELETE
    private static void deleteSubscriber() {
        System.out.println("Testing deleteSubscriber API...");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/subscriber/5");
        restTemplate.delete(REST_SERVICE_URI + "/subscriber/6");
    }

    // Main
    public static void main(String args[]) {
        getHealth();
        listAllSubscribers();
        //getSubscriber();
        createSubscriber();
        listAllSubscribers();
        updateSubscriber();
        listAllSubscribers();
        deleteSubscriber();
        listAllSubscribers();
        deleteAllSubscribers();
        listAllSubscribers();
    }
}


