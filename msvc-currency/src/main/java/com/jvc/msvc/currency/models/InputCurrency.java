package com.jvc.msvc.currency.models;

public class InputCurrency {
    private Double amountToConvert;
    private String originCurrency;
    private String destinationCurrency;

    public Double getAmountToConvert() {
        return amountToConvert;
    }

    public void setAmountToConvert(Double amountToConvert) {
        this.amountToConvert = amountToConvert;
    }

    public String getOriginCurrency() {
        return originCurrency;
    }

    public void setOriginCurrency(String originCurrency) {
        this.originCurrency = originCurrency;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }
}
