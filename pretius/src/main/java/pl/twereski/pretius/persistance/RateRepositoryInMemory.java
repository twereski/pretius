package pl.twereski.pretius.persistance;

import org.springframework.stereotype.Service;
import pl.twereski.pretius.domain.Currency;
import pl.twereski.pretius.domain.Rate;
import pl.twereski.pretius.domain.RateRepository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateRepositoryInMemory implements RateRepository {

    private final ConcurrentHashMap<Currency, Rate> map = new ConcurrentHashMap<>();

    public void saveAll(List<Rate> rates) {
        rates.forEach(g -> map.put(g.getCurrency(), g));
    }

    @Override
    public Rate getByCode(String code) {
        return map.values().stream().filter(r -> r.getCurrency()
                .getCode().equals(code)).findFirst().orElseThrow(() -> new RuntimeException("dd"));
    }
}
