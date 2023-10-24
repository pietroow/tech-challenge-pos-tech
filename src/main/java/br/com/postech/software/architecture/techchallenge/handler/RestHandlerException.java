package br.com.postech.software.architecture.techchallenge.handler;

import br.com.postech.software.architecture.techchallenge.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.exception.ErrorDetails;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.exception.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleError(Exception exception) {
        ErrorDetails exceptionDetails = new ErrorDetails(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());

        return new ResponseEntity<ErrorDetails>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDetails> handleApiException(BusinessException apiException) {
        ErrorDetails apiExceptionDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                apiException.getMessage());

        return new ResponseEntity<ErrorDetails>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorDetails> handleApiException(PersistenceException exception) {
        ErrorDetails apiExceptionDetails = new ErrorDetails(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());

        return new ResponseEntity<ErrorDetails>(apiExceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleApiException() {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleApiException(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        List<String> erros = bindingResult.getFieldErrors()
                .stream()
                .map(error -> String.format("%s %s", error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(erros);
    }
}
