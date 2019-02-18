package pl.twereski.pretius.app.nbp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NbpResponse {

    private String table;
    private String no;
    private String effectiveDate;
    private Collection<NbpCurrency> rates;
}
