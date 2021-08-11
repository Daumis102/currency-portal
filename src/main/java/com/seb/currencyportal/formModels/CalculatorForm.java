package com.seb.currencyportal.formModels;

public class CalculatorForm {
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
