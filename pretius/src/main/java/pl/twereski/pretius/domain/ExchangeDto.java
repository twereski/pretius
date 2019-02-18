package pl.twereski.pretius.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeDto {

    private BigDecimal amount;

    private String inCurrency;

    private String outCurrency;
}
