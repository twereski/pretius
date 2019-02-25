package pl.twereski.pretius.domain.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class MoneyDto implements Serializable {

    String code;
    BigDecimal amount;
}
