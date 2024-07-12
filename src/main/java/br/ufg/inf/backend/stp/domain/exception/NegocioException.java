package br.ufg.inf.backend.stp.domain.exception;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class NegocioException extends RuntimeException {
    private HttpStatus status;
    private List<NegocioError> errors;

    public NegocioException() {
    }

    public NegocioException(String codeMessage) {
        this(codeMessage, (HttpStatus) null, (String) "");
    }

    public NegocioException(String codeMessage, HttpStatus status) {
        this(codeMessage, status, "");
    }

    public NegocioException(String codeMessage, HttpStatus status, String developerMessage) {
        this.status = status;
        this.addError(codeMessage, developerMessage);
    }

    public NegocioException(String codeMessage, String... messageParams) {
        this.addError(codeMessage, "", messageParams);
    }

    public NegocioException(String codeMessage, HttpStatus status, String... messageParams) {
        this.addError(codeMessage, "", messageParams);
    }

    public final void addError(String codeMessage) {
        this.addError(codeMessage, "");
    }

    public final void addError(String codeMessage, String developerMessage) {
        this.getErrors().add(new NegocioError(codeMessage, developerMessage, new String[0]));
    }

    public final void addError(String codeMessage, String developerMessage, String... messageParams) {
        this.getErrors().add(new NegocioError(codeMessage, developerMessage, messageParams));
    }

    public HttpStatus getStatus() {
        if (this.status == null) {
            this.status = HttpStatus.BAD_REQUEST;
        }

        return this.status;
    }

    public List<NegocioError> getErrors() {
        if (this.errors == null) {
            this.errors = new ArrayList();
        }

        return this.errors;
    }
}