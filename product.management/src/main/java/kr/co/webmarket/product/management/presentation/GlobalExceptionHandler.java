package kr.co.webmarket.product.management.presentation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.core.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolateException(
            ConstraintViolationException ex
    ){
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> err = constraintViolations.stream()
                .map(constraintViolation ->
                        extractField(constraintViolation.getPropertyPath())+", "+
                                constraintViolation.getMessage())
                .toList();

        ErrorMessage errorMessage = new ErrorMessage(err);
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> err = fieldErrors.stream()
                .map(
                        fieldError ->
                                fieldError.getField()+", "+fieldError.getDefaultMessage()
                ).toList();
        ErrorMessage errMsg = new ErrorMessage(err);
        return new ResponseEntity(errMsg,HttpStatus.BAD_REQUEST);
    }

    private String extractField(Path path){
        String[] splittedArray = path.toString().split("[.]");
        int lastIdx = splittedArray.length - 1;
        return splittedArray[lastIdx];
    }
}
