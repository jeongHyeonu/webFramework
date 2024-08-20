package kr.co.webmarket.product.management.domain;

import kr.co.webmarket.product.management.presentation.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message){
        super(message);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex){
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorMessage errMsg = new ErrorMessage(errors);
        return new ResponseEntity<>(errMsg, HttpStatus.NOT_FOUND);
    }
}
