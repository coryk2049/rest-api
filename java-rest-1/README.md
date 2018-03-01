## java-rest-1

Simple implementation of Subscriber REST API using Java SpringBoot Actuator modules.

### Prerequisites
- Java JDK 1.8.x
- Apache Maven 3.3.x
- cURL 7.47.x
- Postman 1.6.15

### Model

```
Objects:
  subscriber - List
            id - Long (auto increment)
          name - String
         phone - String
         email - String
```

### API Operations

```
  POST: /SpringBootSubscriberRest/api/v1/subscriber/          - Create new subscriber profile
   PUT: /SpringBootSubscriberRest/api/v1/subscriber/<long:id> - Update subscriber by id
   GET: /SpringBootSubscriberRest/api/v1/subscriber/<long:id> - Get subscriber by id
DELETE: /SpringBootSubscriberRest/api/v1/subscriber/<long:id> - Delete subscriber by id
   GET: /SpringBootSubscriberRest/api/v1/subscriber           - Get subscriber repository
DELETE: /SpringBootSubscriberRest/api/v1/subscriber           - Delete subscriber repository
```


### Execute Program
1) Package and then start REST server:

```
# mvn package && java -jar target/subscriber-rest-service-1.0.0.jar
```

2) Smoke test REST server:
```
# curl http://localhost:9090/SpringBootSubscriberRest/api/v1/../../health
{"status":"UP"}
```

3) Execute `SpringBootSubscriberRestTestClient` for sanity API testing via `RestTemplate` library  

### References
- https://www.getpostman.com/
- https://spring.io/guides/gs/spring-boot/
- https://spring.io/guides/gs/actuator-service/
- https://github.com/spring-projects/spring-boot/
- http://websystique.com/spring-boot/spring-boot-rest-api-example/
- http://zetcode.com/springboot/restcontroller/
- https://www.mkyong.com/spring-boot/spring-boot-profile-based-properties-and-yaml-example/
