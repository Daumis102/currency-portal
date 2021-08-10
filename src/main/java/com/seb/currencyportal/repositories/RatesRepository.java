package com.seb.currencyportal.repositories;

import org.springframework.data.repository.CrudRepository;  
import com.seb.currencyportal.models.Rate;
public interface RatesRepository extends CrudRepository<Rate, Integer>  
{  
}  