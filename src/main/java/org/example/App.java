package org.example;

import org.example.configuration.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

public class App {

    public static void main( String[] args )
    {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Communication communication = context.getBean("communication", Communication.class);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<User>>() {});

        HttpHeaders headers = responseEntity.getHeaders();
        String cookie = headers.getFirst(headers.SET_COOKIE);
        System.out.println(cookie);

        User user = new User(3L, "James", "Brown", (byte) 10);
        communication.saveUser(user, cookie);

        User user1 = new User(3L, "Thomas", "Shelby", (byte) 10);
        communication.editUser(user1, cookie);

        communication.deleteUser("3", cookie);


    }


}
