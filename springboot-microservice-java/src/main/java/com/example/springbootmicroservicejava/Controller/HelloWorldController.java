package com.example.springbootmicroservicejava.Controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.springbootmicroservicejava.Data.HelloWorldBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    //@RequestMapping(method=RequestMethod.GET, path="/hello-world") this is old version anotation
    @GetMapping("/hello-world")
    public String helloWorld() {
        return new String();
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("hello world bean");
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale local = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", local);
        //return "Hello World";
        //good.morning.message
    }

    
}