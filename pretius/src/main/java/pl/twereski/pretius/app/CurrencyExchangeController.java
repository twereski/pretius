package pl.twereski.pretius.app;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.twereski.pretius.app.nbp.NbpFacade;
import pl.twereski.pretius.domain.Converter;
import pl.twereski.pretius.domain.dto.ExchangeDto;
import pl.twereski.pretius.domain.dto.MoneyDto;


@AllArgsConstructor
@RestController
@RequestMapping("/exchange")
public class CurrencyExchangeController {

    private final NbpFacade facade;
    private final Converter converter;

    @PostMapping("/update")
    void update() {
        facade.updateRates();
    }

    @PostMapping
    MoneyDto exchange(@RequestBody ExchangeDto exchangeDto) {
        return converter.convert(exchangeDto);
    }
}
