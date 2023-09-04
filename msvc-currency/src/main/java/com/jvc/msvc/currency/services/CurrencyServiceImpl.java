package com.jvc.msvc.currency.services;

import com.jvc.msvc.currency.models.InputCurrency;
import com.jvc.msvc.currency.models.OutputCurrency;
import com.jvc.msvc.currency.models.entity.Currency;
import com.jvc.msvc.currency.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    @Autowired
    private CurrencyRepository repository;

    @Override
    @Transactional(readOnly = true)
    public OutputCurrency convertCurrency(InputCurrency input) {
        OutputCurrency output = new OutputCurrency();
        List<Currency> originCurrency = repository.searchCurrencies(input.getOriginCurrency());
        List<Currency> destinationCurrency = repository.searchCurrencies(input.getDestinationCurrency());
        if (originCurrency.isEmpty() || destinationCurrency.isEmpty()) {
            throw new IllegalArgumentException("Moneda no encontrada");
        }

        Double amount = input.getAmountToConvert();
        Double fromRate = originCurrency.get(0).getValueExchange();
        Double toRate = destinationCurrency.get(0).getValueExchange();
        Double exchangeRate = fromRate / toRate;
        Double convertedAmount = amount * exchangeRate;

        output.setAmountToConvert(input.getAmountToConvert());
        output.setOriginCurrency(input.getOriginCurrency());
        output.setDestinationCurrency(input.getDestinationCurrency());
        output.setConvertedAmount(Math.round(convertedAmount*1000.0)/1000.0);
        output.setExchangeRate(Math.round(exchangeRate*10000.0)/10000.0);
        return output;
    }

    @Override
    @Transactional
    public void changeExchangeRate(Currency currency) {
        repository.save(currency);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Currency> getCurrencyById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Currency> findCurrency(String abbreviation) {
        return repository.searchCurrencies(abbreviation);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return repository.findAll();
    }
}
