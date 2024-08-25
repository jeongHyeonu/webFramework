package study.hyeonu.config.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import study.hyeonu.config.error.exception.BusinessBaseException;

@Slf4j
@ControllerAdvice // 모든 컨트롤러에서 발생하는 예외 잡아서 처리
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException e){
        log.error("httpRequestMethodNotSupportedException", e);
        return createErrorResponseEntity(ErrorCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(BusinessBaseException.class)
    protected ResponseEntity<ErrorResponse> handle(BusinessBaseException e){
        log.error("BusinessException", e);
        return createErrorResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handle(Exception e){
        log.error("Exception", e);
        return createErrorResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> createErrorResponseEntity(ErrorCode err){
        return new ResponseEntity<>(ErrorResponse.of(err),err.getStatus());
    }
}
