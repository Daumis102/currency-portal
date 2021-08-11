package com.seb.currencyportal.models;


import javax.persistence.Column;  
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.seb.currencyportal.models.compositeKeys.RateId;  

@Entity
@Table(name = "Rates")
@IdClass(RateId.class) // Allos composite primary key (currency and date)
public class Rate{

    @Column
    @Id
    String currency;

    @Id
    @Column
    String date;

    @Column
    Float rate;


    public Rate(String currency, String date, Float rate) {
        this.currency = currency;
        this.date = date;
        this.rate = rate;
    }

    public Rate(){};
    
    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getRate() {
        return this.rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

}