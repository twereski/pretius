package pl.twereski.pretius.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.twereski.pretius.domain.dto.ExchangeDto;
import pl.twereski.pretius.domain.dto.MoneyDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class Converter {

    private final RateRepository repository;

    public MoneyDto convert(ExchangeDto exchangeDto) {
        Rate in = repository.getByCode(exchangeDto.getInCurrency());
        Rate out = repository.getByCode(exchangeDto.getOutCurrency());

        BigDecimal amount = exchangeDto.getAmount()
                .multiply(in.getValue())
                .divide(out.getValue(), RoundingMode.valueOf(4));

        return new MoneyDto(exchangeDto.getOutCurrency(), amount);
    }
}
