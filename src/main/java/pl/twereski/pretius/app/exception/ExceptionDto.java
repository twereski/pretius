package pl.twereski.pretius.app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ExceptionDto {
    private String message;
}
