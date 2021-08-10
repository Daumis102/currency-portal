package com.seb.currencyportal.models.compositeKeys;

import java.io.Serializable;
import java.util.Objects;

public class RateId implements Serializable {
    private String currency;

    private String date;

    public RateId(){};

    public RateId(String currency, String date) {
        this.currency = currency;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateId rateId = (RateId) o;
        return currency.equals(rateId.currency) &&
                date.equals(rateId.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, date);
    }
}