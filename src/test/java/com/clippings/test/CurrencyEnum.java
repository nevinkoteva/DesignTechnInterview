package com.clippings.test;

import java.util.Arrays;
import java.util.Optional;

public enum CurrencyEnum {

    EUR("Euro (EUR)", "€"),
    GBP("British Pound (GBP)","£");

    private String value;
    private String currencySymbol;

    CurrencyEnum(String value, String currencySymbol) {
        this.value = value;
        this.currencySymbol = currencySymbol;
    }

    public static Optional<CurrencyEnum> valueOfEnum(String name) {
        return Arrays.stream(values())
                .filter(typeEnum -> typeEnum.value.toLowerCase().equals(name.toLowerCase()))
                .findFirst();
    }

    public String getValue() {
        return this.value;
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

}
