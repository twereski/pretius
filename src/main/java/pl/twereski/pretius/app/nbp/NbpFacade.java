package pl.twereski.pretius.app.nbp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.twereski.pretius.app.exception.ExchangeException;
import pl.twereski.pretius.app.nbp.dto.NbpCurrency;
import pl.twereski.pretius.app.nbp.dto.NbpResponse;
import pl.twereski.pretius.domain.Rate;
import pl.twereski.pretius.persistance.RateRepositoryInMemory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class NbpFacade {

    @Value("${nbp.table.a}")
    private String tableA;

    @Value("${nbp.table.b}")
    private String tableB;

    private final NbpInvoker invoker;
    private final RateRepositoryInMemory rateRepository;

    public NbpFacade(NbpInvoker invoker, RateRepositoryInMemory rateRepository) {
        this.invoker = invoker;
        this.rateRepository = rateRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 12 16 * * ?")
    public void updateRates() {

        List<Rate> rateList = getAllTablesData()
                .map(r -> new Rate(r.getCode(), r.getMid()))
                .collect(Collectors.toList());

        rateRepository.saveAll(rateList);
    }

    private Stream<NbpCurrency> getAllTablesData() {

        try {
            NbpResponse responseA = invoker.getCurrenciesRate(tableA).
                    stream().findFirst().orElseThrow(() -> new ExchangeException("No data from NBP - Table A"));

            NbpResponse responseB = invoker.getCurrenciesRate(tableB).
                    stream().findFirst().orElseThrow(() -> new RuntimeException("No data from NBP - Table B"));

            return Stream.concat(responseA.getRates().stream(), responseB.getRates().stream());

        } catch (RuntimeException e) {
            log.error("Update data from NBP failed.", e);
        }

        return Stream.empty();
    }
}
