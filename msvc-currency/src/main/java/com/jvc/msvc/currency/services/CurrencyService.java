package com.jvc.msvc.currency.services;

import com.jvc.msvc.currency.models.InputCurrency;
import com.jvc.msvc.currency.models.OutputCurrency;
import com.jvc.msvc.currency.models.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    public OutputCurrency convertCurrency(InputCurrency input);
    public void changeExchangeRate(Currency currency);
    public Optional<Currency> getCurrencyById(Long id);
    public List<Currency> findCurrency(String abbreviation);
    public List<Currency> getAllCurrencies();
}
