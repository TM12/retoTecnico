package com.jvc.msvc.currency.controllers;

import com.jvc.msvc.currency.models.InputCurrency;
import com.jvc.msvc.currency.models.OutputCurrency;
import com.jvc.msvc.currency.models.entity.Currency;
import com.jvc.msvc.currency.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @PostMapping("/converted")
    public ResponseEntity<?> convertedAmount(@RequestBody InputCurrency input){
        try{
            OutputCurrency output = service.convertCurrency(input);
            if(output!=null){
                return ResponseEntity.ok(output);
            }
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error",ex.getMessage()));
        }
    }

    @PostMapping("/updateCurrency")
    public ResponseEntity<?> updateCurrency(@RequestBody Currency currency){
        List<Currency> currencies= service.findCurrency(currency.getAbbreviation());
        if(!currencies.isEmpty()){
            Currency currencyDb = currencies.get(0);
            currencyDb.setValueExchange(currency.getValueExchange());
            service.changeExchangeRate(currencyDb);
            return ResponseEntity.ok(currencyDb);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/currencies")
    public ResponseEntity<?> getAllCurrencies(){
        return ResponseEntity.ok(service.getAllCurrencies());
    }

}
