package com.seb.currencyportal.services;

import java.util.ArrayList;  
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
import com.seb.currencyportal.models.Rate;  
import com.seb.currencyportal.repositories.RatesRepository;  

@Service
public class RateDbService {

  @Autowired
  RatesRepository rateRepository;

  public List<Rate> getAllRates() {
    List<Rate> rates = new ArrayList<Rate>();
    rateRepository.findAll().forEach(rate -> rates.add(rate));
    return rates;
  }

  // //getting a specific record
  // public Student getRateByCurrency(int id)
  // {
  // return studentRepository.findById(id).get();
  // }
  public void saveOrUpdate(Rate rate) {
    rateRepository.save(rate);
  }

  //deleting a specific record
  public void delete(int id) {
    rateRepository.deleteById(id);
  }
}
