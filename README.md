# pretius

Running the app:
```
mvn spring-boot:run
```

A sample *exchange* :

```
curl -X POST "http://localhost:8080/exchange" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"amount\": 110,  \"inCurrency\": \"USD\",  \"outCurrency\": \"AUD\"}" 
```

