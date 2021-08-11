# A website for checking currency rates from Lietuvos Bankas

## Features

`/rates` endpoint shows newest rates for every currency available in Lietuvos bankas.  
`/rate-history?currency={currency}` shows the rate history for specified `currency` available in the DB  
`/currency-calculator` Allows converting specified amount of EUR currency to a selected currency.  

Rates are automatically obtained from Lietuvos Bankas and added to the database every midnight.

## Notes
From the task I have understood that all the data should be taken from the H2 database for display purposes (ex. showing history), instead of querrying the Lietuvos Bankas website. Due to this, history page initially shows only one rate, which is prepopulated with newest rates for each currency during the application startup. After a few days of the continuous application work, there would be more rates displayed in the history page.

## Startup

Start the application using `./mvnw spring-boot:run`