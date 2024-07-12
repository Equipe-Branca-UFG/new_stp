package br.ufg.inf.backend.stp.infra;

import br.ufg.inf.backend.stp.domain.exception.ExceptionDTO;
import br.ufg.inf.backend.stp.domain.exception.NegocioError;
import br.ufg.inf.backend.stp.domain.exception.NegocioException;
import br.ufg.inf.backend.stp.infra.error.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    private static final String DEFAULT_MESSAGE_UNEXPECTED_ERROR = " Erro inesperado";
    private static final String INVALID_DATA_ACCESS = "invalid-data-access";
    private static final String NO_MESSSAGE_AVAILABLE = " Não foi encontrado nenhuma mensagem para o código: ";

    private final MessageSource apiErrorMessageSource;

    public ControllerExceptionHandler(MessageSource apiErrorMessageSource) {
        this.apiErrorMessageSource = apiErrorMessageSource;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> threatDuplicateEntry(DataIntegrityViolationException exception) {
        ExceptionDTO duplicateUserException = new ExceptionDTO(new Date(), exception.getMessage());
        return ResponseEntity.badRequest().body(duplicateUserException);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> threat404(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDTO> badResquestException(Exception exception) {
        ExceptionDTO generalException = new ExceptionDTO(new Date(), exception.getMessage());
        return ResponseEntity.badRequest().body(generalException);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> badResquestBodyException(HttpMessageNotReadableException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(), "Obrigatório enviar o body nesta requisição");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler (NegocioException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(NegocioException exception, Locale locale) {

        Stream<NegocioError> errors = exception.getErrors().stream();
        List<ErrorResponse.ApiError> apiErrors = errors
                .map(businessError -> toApiError(businessError.getCodeMessage(), locale, businessError.getDeveloperMessage(), businessError.getMessageParams()))
                .collect(toList());

        final ErrorResponse errorResponse = ErrorResponse.of(exception.getStatus(), apiErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ErrorResponse.ApiError toApiError(String code, Locale locale, String developerMessage, Object... args) {
        String message;
        try {
            message = apiErrorMessageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            LOG.error("Could not find any message for {} code under {} locale", code, locale);
            message = DEFAULT_MESSAGE_UNEXPECTED_ERROR;
            developerMessage += NO_MESSSAGE_AVAILABLE + code;
        }

        return getApiError(code, message, developerMessage);
    }

    private ErrorResponse.ApiError getApiError(String error, String message, String developerMessage) {
        return new ErrorResponse.ApiError(error, message, developerMessage);
    }
}
