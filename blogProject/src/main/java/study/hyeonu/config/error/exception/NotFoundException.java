package study.hyeonu.config.error.exception;

import study.hyeonu.config.error.ErrorCode;

public class NotFoundException extends BusinessBaseException{
    public NotFoundException(ErrorCode errorCode){
        super(errorCode.getMessage(),errorCode);
    }
    public NotFoundException(){
        super(ErrorCode.NOT_FOUND);
    }
}
