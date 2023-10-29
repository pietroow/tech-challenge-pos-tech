package br.com.postech.software.architecture.techchallenge.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.postech.software.architecture.techchallenge.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.exception.ErrorDetails;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.exception.PersistenceException;

@ControllerAdvice
public class RestHandlerException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleError(Exception exception) {
		ErrorDetails execptionDetails = new ErrorDetails(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exception.getMessage());
		
		System.out.println(exception.getMessage());
		
		return new ResponseEntity<ErrorDetails>(execptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDetails> handleBusinessException(BusinessException apiExecption) {
		ErrorDetails apiExecptionDetails = new ErrorDetails(
				HttpStatus.BAD_REQUEST.value(), 
				apiExecption.getMessage());

		return new ResponseEntity<ErrorDetails>(apiExecptionDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<ErrorDetails> handlePersistenceException(PersistenceException execption) {
		ErrorDetails apiExecptionDetails = new ErrorDetails(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				execption.getMessage());

		return new ResponseEntity<ErrorDetails>(apiExecptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException exception) {
    	ErrorDetails apiExceptionDetails = new ErrorDetails(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage());

        return new ResponseEntity<ErrorDetails>(apiExceptionDetails, HttpStatus.NOT_FOUND);
    }
}
