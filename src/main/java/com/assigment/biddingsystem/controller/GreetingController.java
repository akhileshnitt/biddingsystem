package com.assigment.biddingsystem.controller;

import com.assigment.biddingsystem.service.GreetingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingClient greetingClient;

    @GetMapping("/get-greeting/{username}")
    public String getGreeting( @PathVariable("username") String username) {
        return greetingClient.greeting(username);

    }
}
