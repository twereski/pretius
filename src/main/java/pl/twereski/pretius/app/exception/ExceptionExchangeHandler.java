package pl.twereski.pretius.app.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionExchangeHandler {

    @ExceptionHandler(ExchangeException.class)
    protected ResponseEntity<ExceptionDto> handleExchange(ExchangeException ex) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonProcessingException.class)
    protected ResponseEntity<ExceptionDto> handleJsonException(JsonProcessingException ex) {
        return new ResponseEntity<>(
                new ExceptionDto("Bad request parameter - " + ex.getOriginalMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
