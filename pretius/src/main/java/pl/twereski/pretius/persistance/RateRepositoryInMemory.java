package pl.twereski.pretius.persistance;

import org.springframework.stereotype.Service;
import pl.twereski.pretius.app.exception.ExchangeException;
import pl.twereski.pretius.domain.Currency;
import pl.twereski.pretius.domain.Rate;
import pl.twereski.pretius.domain.RateRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateRepositoryInMemory implements RateRepository {

    private final ConcurrentHashMap<Currency, Rate> map = new ConcurrentHashMap<>();

    public RateRepositoryInMemory() {
        map.put(new Currency("PLN"), new Rate("PLN", "1.00"));
    }

    public void saveAll(List<Rate> rates) {
        rates.forEach(g -> map.put(g.getCurrency(), g));
    }

    @Override
    public Rate getByCode(String code) {
        Currency currency = new Currency(code);
        return Optional.ofNullable(map.get(currency)).orElseThrow(
                () -> new ExchangeException("Wrong currency code: " + code)
        );
    }
}
