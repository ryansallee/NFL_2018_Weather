# NFL 2018 Weather App

## Question
The NFL 2018 Weather App attempts to assist in answering the question "Did weather conditions affect scoring in NFL games in 2018? by
providing users a statistical charts of NFL Scoring and weather data.

## Methodology
To provide a user these charts, first the app reads data from a CSV (2018_nfl_results.csv) that contains the final results
of every regular season game in 2018. It then makes calls to the Google Geocoiding API to obtain the latitude and longitude
of the stadia these games to make an API call to the Dark Sky API to obtain the weather conditions at the start of the game.
Both the game results and their associated weather conditions are combined and then persisted to a database where this data
can be retrieved for use in statistical charts based on scoring and temperature, general conditions (i.e. indoors, precipitation,
no precipitation) and both temperature and conditions.

## Dependencies
* JDK 12
* Maven
* JavaFx 12
* OpenCSV
* JSONPath
* Dark-Sky Forecast API
* Hibernate 5.4.3
* H2 Database


## Data Sources:
* [2018 NFL Weekly League Schedule | Pro-Football Reference](https://www.pro-football-reference.com/years/2018/games.htm)
* [Google Geocoding API](https://developers.google.com/maps/documentation/geocoding/start?utm_source=google&utm_medium=cpc&utm_campaign=FY18-Q2-global-demandgen-paidsearchonnetworkhouseads-cs-maps_contactsal_saf&utm_content=text-ad-none-none-DEV_c-CRE_315916117598-ADGP_Hybrid+%7C+AW+SEM+%7C+BKWS+~+Google+Maps+Geocoding+API-KWID_43700039136946117-kwd-300650646186-userloc_9014241&utm_term=KW_google%20geocoding%20api-ST_google+geocoding+api&gclid=CN2w8uHzv-MCFWGlZQodAO4Bww)
* [Dark Sky](https://darksky.net/dev)

## Special Notes
Please note that the H2 database that is created by the app will be stored in your user root folder(e.g. C:\Users\[your_
user_name]). If you do not wish to retain these database files, you will need to delete the following files from your user
root folder:
* game.mv
* game.trace
* .h2.server
