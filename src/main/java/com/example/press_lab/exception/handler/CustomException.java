package com.example.press_lab.exception.handler;


import com.example.press_lab.exception.GenericException;
import com.example.press_lab.util.LocaleResolverUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class CustomException extends ResponseEntityExceptionHandler {


    private final LocaleResolverUtil localeResolverUtil;

    @ExceptionHandler(GenericException.class)
    public ProblemDetail handleGenericException(GenericException ex) {
        log.info("handleGenericException {}", ex.getMessage());

        Locale locale = LocaleContextHolder.getLocale();

        String localizedMessage = localeResolverUtil.getMessage(ex.getMessage(), locale);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getHttpStatus(), localizedMessage);

        problemDetail.setTitle("Error");
        problemDetail.setInstance(URI.create(ex.getClass().getSimpleName()));

        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest()
                .body(errors);
    }

}