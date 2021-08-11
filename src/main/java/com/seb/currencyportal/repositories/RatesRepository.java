package com.seb.currencyportal.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seb.currencyportal.models.Rate;

@Repository
public interface RatesRepository extends CrudRepository<Rate, Integer>  
{  
    @Query(value = "SELECT * FROM Rates INNER JOIN (SELECT currency, max(date) date FROM Rates GROUP BY Currency) using(currency, date)", nativeQuery = true)
    public Iterable<Rate> getAllNewest();

    @Query(value = "SELECT * FROM Rates INNER JOIN (SELECT currency, max(date) date FROM Rates WHERE currency = :currency GROUP BY Currency) using(currency, date)", nativeQuery = true)
    public Rate getNewest(@Param("currency") String currency);

    
    @Query("FROM Rate WHERE currency=:currency")
    public Iterable<Rate> getHistory(@Param("currency") String currency);

    @Query(value = "SELECT DISTINCT Currency From Rates", nativeQuery = true)
    public Iterable<String> getCurrenciesList();
}  