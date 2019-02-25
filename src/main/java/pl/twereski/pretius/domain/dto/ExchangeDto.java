package pl.twereski.pretius.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ExchangeDto {

    private BigDecimal amount;

    private String inCurrency;

    private String outCurrency;
}
