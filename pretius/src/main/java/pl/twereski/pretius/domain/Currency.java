package pl.twereski.pretius.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Currency {
    private String name;

    @Getter
    private String code;
}
