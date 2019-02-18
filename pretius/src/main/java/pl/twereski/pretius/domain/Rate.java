package pl.twereski.pretius.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Rate {

    private Currency currency;

    private BigDecimal value;

    public Rate(String name, String code, String mid) {
        this.currency = new Currency(name, code);
        this.value = new BigDecimal(mid);
    }
}
