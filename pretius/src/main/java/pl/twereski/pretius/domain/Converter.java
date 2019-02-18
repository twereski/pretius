package pl.twereski.pretius.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class Converter {

    private final RateRepository repository;

    public Money convert(ExchangeDto exchangeDto) {
        Rate in = repository.getByCode(exchangeDto.getInCurrency());
        Rate out = repository.getByCode(exchangeDto.getOutCurrency());

        BigDecimal amount = exchangeDto.getAmount()
                .multiply(in.getValue())
                .divide(out.getValue(), RoundingMode.valueOf(4));

        return new Money(exchangeDto.getOutCurrency(), amount);
    }
}
