package com.example.springbootmicroservicejava.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmicroservicejava.Configurations.CurrencyServiceConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CurrencyConfigurationController {
    @Autowired
    private CurrencyServiceConfiguration currencyServiceConfiguration;

    // public CurrencyConfigurationController(CurrencyServiceConfiguration ccurrencyServiceConfiguration ){
    //     this.currencyServiceConfiguration = ccurrencyServiceConfiguration;
    // }

    @GetMapping("/currency-configuration")
    public CurrencyServiceConfiguration retriveAllCurrencyConfiguration(){
        return currencyServiceConfiguration;
    }
}
