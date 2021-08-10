package com.seb.currencyportal.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seb.currencyportal.models.Rate;

@Repository
public interface RatesRepository extends CrudRepository<Rate, Integer>  
{  
    // @Query("SELECT new com.seb.currencyportal.models.Rate(currency, MAX(date), rate) FROM Rate GROUP BY date")
    @Query(value = "SELECT * FROM Rates INNER JOIN (SELECT currency, max(date) date FROM Rates GROUP BY Currency) using(currency, date)", nativeQuery = true)
    public Iterable<Rate> getAllNewest();

    @Query("SELECT MAX(date) FROM Rate GROUP BY date")
    public Iterable<String> test();
}  