package com.rafael.bankCurrencies.bankCurrencies.controllers;

import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import com.rafael.bankCurrencies.bankCurrencies.models.Limit;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MainController {
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("Yaaah, I am running........");
        Client obj = new Client();
        obj.setId(1);
        System.out.println("setID " + obj.getId());
        Limit obj1 = new Limit();
        obj1.setCurrencyShortname("USD");
        System.out.println("setSuurency " + obj1.getCurrencyShortname());
        
    }
}
