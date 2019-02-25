package pl.twereski.pretius.domain;

import lombok.Getter;
import pl.twereski.pretius.app.exception.ExchangeException;

import java.math.BigDecimal;

@Getter
public class Rate {

    private Currency currency;

    private BigDecimal value;

    public Rate(String code, String mid) {
        this.currency = new Currency(code);
        try {
            this.value = new BigDecimal(mid);
        } catch (NumberFormatException e) {
            throw new ExchangeException("Wrong exchange rate format: " + mid, e);
        }
    }
}
