package com.oliveira.willy.starwarsresistance.handler;

import com.oliveira.willy.starwarsresistance.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RebelNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerRebelNotFoundException(RebelNotFoundException rebelNotFoundException) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Not found Exception, Rebel not found!")
                .details(rebelNotFoundException.getMessage())
                .developerMessage(rebelNotFoundException.getClass().getName())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidReportException.class)
    public ResponseEntity<ExceptionDetails> handlerInvalidReportException(InvalidReportException invalidReportException) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Invalid report Exception, Report invalid")
                .details(invalidReportException.getMessage())
                .developerMessage(invalidReportException.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateItemsInventoryException.class)
    public ResponseEntity<ExceptionDetails> handlerDuplicateItemsInventoryException(DuplicateItemsInventoryException duplicateItemsInventoryException) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Duplicate items inventory Exception")
                .details(duplicateItemsInventoryException.getMessage())
                .developerMessage(duplicateItemsInventoryException.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTradeException.class)
    public ResponseEntity<ExceptionDetails> handlerInvalidTradeException(InvalidTradeException invalidTradeException) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Invalid trade Exception, Trade invalid")
                .details(invalidTradeException.getMessage())
                .developerMessage(invalidTradeException.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetails> handlerUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Username Already Exists Exception, Username unavailable")
                .details(userAlreadyExistsException.getMessage())
                .developerMessage(userAlreadyExistsException.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionDetails> handlerInvalidTokenException(InvalidTokenException invalidTokenException) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .title("InvalidTokenException, Token invalid")
                .details(invalidTokenException.getMessage())
                .developerMessage(invalidTokenException.getClass().getName())
                .build(), HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(exceptionDetails, headers, status);
    }


}
