package com.cognifide.rbac.poc.demo.endpoint;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private GreetingService greetingService;

    GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping(value = {"/", "/home"})
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "home";
    }

    @GetMapping("/home/*")
    public String hello2(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "home2";
    }

    @GetMapping("/scope1")
    public String scope1(Authentication authentication, Principal principal) {
        principal.getName();
        return greetingService.getSth();
    }

    @GetMapping("/scope2")
    public String scope2(@RequestParam String scope) {
        return greetingService.getSth(scope);
    }
}
