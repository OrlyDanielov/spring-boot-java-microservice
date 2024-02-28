package com.example.springbootmicroservicejava.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmicroservicejava.Data.HelloWorldBean;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
class HelloWorldController {

    //@RequestMapping(method=RequestMethod.GET, path="/hello-world") this is old version anotation
    @GetMapping("/hello-world")
    public String helloWorld() {
        return new String();
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("hello world bean");
    }
    
}