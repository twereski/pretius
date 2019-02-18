package pl.twereski.pretius.app.nbp;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.twereski.pretius.app.nbp.dto.NbpResponse;
import pl.twereski.pretius.domain.NbpService;
import pl.twereski.pretius.domain.Rate;
import pl.twereski.pretius.persistance.RateRepositoryInMemory;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NbpFacade implements NbpService {

    private static final String TABLE_A = "http://api.nbp.pl/api/exchangerates/tables/A";
    private static final String TABLE_B = "http://api.nbp.pl/api/exchangerates/tables/B";

    private final NbpInvoker invoker;
    private final RateRepositoryInMemory rateRepository;

    @Scheduled(cron = "0 1 1 * * ?")
    public void updateRates() {
        NbpResponse response = invoker.getCurrenciesRate(TABLE_A).
                stream().findFirst().orElseThrow(() -> new RuntimeException("No current rates"));


        List<Rate> rateList = response.getRates().stream()
                .map(r -> new Rate(r.getCurrency(), r.getCode(), r.getMid()))
                .collect(Collectors.toList());

        rateRepository.saveAll(rateList);
    }
}
