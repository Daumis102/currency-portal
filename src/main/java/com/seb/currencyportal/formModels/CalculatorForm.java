package com.seb.currencyportal.formModels;

import javax.validation.constraints.Pattern;

public class CalculatorForm {
    @Pattern(regexp="^\\d*(\\.\\d{0,2})?$", message = "Please enter a number")
    String amount;
    String currency;


    public CalculatorForm() {
    }

    public CalculatorForm(String value, String currency) {
        this.amount = value;
        this.currency = currency;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String value) {
        this.amount = value;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Float getAmountFloat(){
        return Float.parseFloat(amount);
    }

}
