package pl.twereski.pretius.app.nbp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NbpCurrency {

    private String currency;
    private String code;
    private String mid;
}
