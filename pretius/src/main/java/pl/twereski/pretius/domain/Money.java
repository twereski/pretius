package pl.twereski.pretius.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Money implements Serializable {

    String code;
    BigDecimal amount;
}
