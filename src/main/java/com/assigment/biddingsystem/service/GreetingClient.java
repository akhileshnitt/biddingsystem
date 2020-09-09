package com.assigment.biddingsystem.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("rest-producer")
public interface GreetingClient {

    @GetMapping("/greeting/{username}")
    String greeting(@PathVariable("username") String username);
}