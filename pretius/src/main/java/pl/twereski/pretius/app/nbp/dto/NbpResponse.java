package pl.twereski.pretius.app.nbp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class NbpResponse {

    private String table;
    private String no;
    private String effectiveDate;
    private Collection<NbpCurrency> rates;
}
