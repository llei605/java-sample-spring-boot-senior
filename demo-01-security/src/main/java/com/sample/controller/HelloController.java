package com.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        System.out.println("Index");
        return "Index";
    }

    @GetMapping("/aaa/{id}")
    public String aaa1(@PathVariable("id") String id) {
        System.out.println("aaa => " + id);
        return "aaa => " + id;
    }
    @GetMapping("/bbb/{id}")
    public String bbb1(@PathVariable("id") String id) {
        System.out.println("bbb => " + id);
        return "bbb => " + id;
    }
}
