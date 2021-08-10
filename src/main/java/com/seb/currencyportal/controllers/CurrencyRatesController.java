package com.seb.currencyportal.controllers;

import com.seb.currencyportal.models.Rate;
import com.seb.currencyportal.services.RateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyRatesController {

	@Autowired
	RateService rateService;

	@GetMapping("/rates")
	public String getRates() {
		

		return "Hello world";
	}
}