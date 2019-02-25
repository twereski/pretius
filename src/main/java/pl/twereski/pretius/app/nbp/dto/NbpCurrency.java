package pl.twereski.pretius.app.nbp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NbpCurrency {

    private String currency;
    private String code;
    private String mid;
}
