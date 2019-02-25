package pl.twereski.pretius.app;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.twereski.pretius.domain.Converter;
import pl.twereski.pretius.domain.dto.ExchangeDto;
import pl.twereski.pretius.domain.dto.MoneyDto;


@AllArgsConstructor
@RestController
@RequestMapping("/exchange")
public class CurrencyExchangeController {

    private final Converter converter;

    @PostMapping
    MoneyDto exchange(@RequestBody ExchangeDto exchangeDto) {
        return converter.convert(exchangeDto);
    }
}
