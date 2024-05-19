package br.com.postech.software.architecture.techchallenge.pagamento.handler;

import br.com.postech.software.architecture.techchallenge.pagamento.exception.ErrorDetails;
import br.com.postech.software.architecture.techchallenge.pagamento.exception.NotFoundException;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleError(Exception exception) {
        ErrorDetails exceptionDetails = new ErrorDetails(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException exception) {
        ErrorDetails apiExceptionDetails = new ErrorDetails(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage());

        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();
        List<String> erros = bindingResult.getFieldErrors()
                .stream()
                .map(error -> String.format("%s %s", error.getField(), error.getDefaultMessage()))
                .toList();

        Gson gson = new Gson();
        String jsonArray = gson.toJson(erros);

        ErrorDetails methodArgumentNotValid = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), jsonArray);

        return new ResponseEntity<>(methodArgumentNotValid, HttpStatus.BAD_REQUEST);
    }
}
