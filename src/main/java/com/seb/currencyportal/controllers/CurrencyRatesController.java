package com.seb.currencyportal.controllers;

import java.util.List;

import javax.validation.Valid;

import com.seb.currencyportal.formModels.CalculatorForm;
import com.seb.currencyportal.models.Rate;
import com.seb.currencyportal.services.RateDbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class CurrencyRatesController {

	@Autowired
	private RateDbService db;

	@GetMapping("/rates")
	public String getRates(Model model) {
		List<Rate> rates =  db.getAllNewest();
		model.addAttribute("rates",rates);

		return "ratesList";
	}

	@GetMapping("/rate-history{currency}")
	public String getCurrencyInfo(@RequestParam String currency, Model model) {
		
		List<Rate> history = db.getHistory(currency);
		
		model.addAttribute("history", history);
		model.addAttribute("currency", currency);
		return "rate-history";
	}

	@GetMapping("/currency-calculator")
	public String curencyCalculator(Model model){
		List<String> currenciesList = db.getCurrenciesList();
		model.addAttribute("currenciesList", currenciesList);
		model.addAttribute("calculatorForm", new CalculatorForm());
		return "currency-calculator";
	}

	@PostMapping("/currency-calculator")
	public String curencyCalculator(@ModelAttribute @Valid CalculatorForm form, BindingResult bindingResult, Model model){
		if (!bindingResult.hasErrors()) {
			Rate rateData = db.getNewest(form.getCurrency());
			Float amount = form.getAmountFloat();
			Float converted = amount * rateData.getRate();
			
			model.addAttribute("rate", rateData);
			model.addAttribute("converted", converted);
			model.addAttribute("calculatorForm", new CalculatorForm());

        }
		List<String> currenciesList = db.getCurrenciesList();
		model.addAttribute("currenciesList", currenciesList);
		
		return "currency-calculator";
	}
}