package com.rafael.bankCurrencies.bankCurrencies.controllers;

import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import com.rafael.bankCurrencies.bankCurrencies.models.Limit;
import com.rafael.bankCurrencies.bankCurrencies.tests.MyTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.junit.runner.Result;  
import org.junit.runner.JUnitCore;  
import org.junit.runner.notification.Failure; 

@Component
public class MainController {
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("Yaaah, I am running........");
//        Result result = JUnitCore.runClasses(MyTest.class);  
//        
//        for (Failure fail : result.getFailures()) {  
//            System.out.println(fail.toString());  
//        }  
//          
//        System.out.println(result.wasSuccessful()); 
        
    }
}
