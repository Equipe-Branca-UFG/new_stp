package br.ufg.inf.backend.stp.domain.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExceptionDTO {

    private final Date timestamp;
    private final String message;

    public ExceptionDTO(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date timestamp() {
        return timestamp;
    }

    public String message() {
        return message;
    }
}