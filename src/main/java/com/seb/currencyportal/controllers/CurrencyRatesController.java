package com.seb.currencyportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CurrencyRatesController {

	@GetMapping("/rates")
	public String getRates(Model model) {


		return "ratesList";
	}
}