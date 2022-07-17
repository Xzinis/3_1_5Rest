package org.example;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Component
public class Communication {

    private RestTemplate restTemplate;

    private final String URL = "http://94.198.50.185:7081/api/users";

    public void saveUser(@RequestBody User user, String cookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity(user, headers);

        System.out.println(restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody());
        System.out.println("Done!");
    }

    public void editUser(@RequestBody User user1, String cookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<User>(user1, headers);

        System.out.println(restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class).getBody());
        System.out.println("Done!");
    }

    public void deleteUser(@PathVariable("id") String id, String cookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<User>(headers);

        System.out.println(restTemplate.exchange("http://94.198.50.185:7081/api/users/"+id, HttpMethod.DELETE, entity, String.class).getBody());
        System.out.println("Done!");
    }
}
