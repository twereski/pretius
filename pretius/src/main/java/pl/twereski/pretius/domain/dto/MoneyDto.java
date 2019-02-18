package pl.twereski.pretius.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MoneyDto implements Serializable {

    String code;
    BigDecimal amount;
}
