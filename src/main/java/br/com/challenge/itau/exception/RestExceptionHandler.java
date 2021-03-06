package br.com.challenge.itau.exception;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({InvalidRegexException.class})
    public ResponseEntity<Object> handlerInvalidRegexException(InvalidRegexException invalidRegexException, WebRequest webRequest) {
        var message = messageSource.getMessage("regex.exception",null, LocaleContextHolder.getLocale());
        log.error(ExceptionUtils.getRootCauseMessage(invalidRegexException));
        var error = new Error(1,message);
          return handleExceptionInternal(invalidRegexException,error,new HttpHeaders(), HttpStatus.OK,webRequest);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Object> handlerNullPointerException(NullPointerException nullPointerException, WebRequest webRequest) {
        var message = messageSource.getMessage("null.pointer.exception",null, LocaleContextHolder.getLocale());
        log.error(ExceptionUtils.getRootCauseMessage(nullPointerException));
        var error = new Error(2,message);
        return handleExceptionInternal(nullPointerException,error,new HttpHeaders(), HttpStatus.OK,webRequest);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handlerException(Exception exception, WebRequest webRequest) {
        var message = messageSource.getMessage("exception",null, LocaleContextHolder.getLocale());
        log.error(ExceptionUtils.getRootCauseMessage(exception));
        var error = new Error(3, message);
        return handleExceptionInternal(exception,error,new HttpHeaders(), HttpStatus.OK,webRequest);
    }

    @Getter
    public static class Error {

        private final Integer code;
        private final String message;

        public Error(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
