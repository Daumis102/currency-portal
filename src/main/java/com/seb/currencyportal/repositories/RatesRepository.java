package com.seb.currencyportal.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seb.currencyportal.models.Rate;

@Repository
public interface RatesRepository extends CrudRepository<Rate, Integer>  
{  
}  