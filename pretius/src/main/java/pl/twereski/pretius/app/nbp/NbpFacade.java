package pl.twereski.pretius.app.nbp;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.twereski.pretius.app.nbp.dto.NbpCurrency;
import pl.twereski.pretius.app.nbp.dto.NbpResponse;
import pl.twereski.pretius.domain.Rate;
import pl.twereski.pretius.persistance.RateRepositoryInMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class NbpFacade {

    private static final String TABLE_A = "http://api.nbp.pl/api/exchangerates/tables/A";
    private static final String TABLE_B = "http://api.nbp.pl/api/exchangerates/tables/B";

    private final NbpInvoker invoker;
    private final RateRepositoryInMemory rateRepository;

    @Scheduled(cron = "0 1 1 * * ?")
    public void updateRates() {

        List<Rate> rateList = getAllTablesData()
                .map(r -> new Rate(r.getCurrency(), r.getCode(), r.getMid()))
                .collect(Collectors.toList());

        rateRepository.saveAll(rateList);
    }

    private Stream<NbpCurrency> getAllTablesData() {

        try {
            NbpResponse responseA = invoker.getCurrenciesRate(TABLE_A).
                    stream().findFirst().orElseThrow(() -> new ExchangeException("No data from NBP"));

            NbpResponse responseB = invoker.getCurrenciesRate(TABLE_B).
                    stream().findFirst().orElseThrow(() -> new RuntimeException("No data from NBP"));

            return Stream.concat(responseA.getRates().stream(), responseB.getRates().stream());

        } catch (RuntimeException e) {
//            log(e);
        }

        return Stream.empty();
    }
}
