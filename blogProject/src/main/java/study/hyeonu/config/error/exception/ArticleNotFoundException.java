package study.hyeonu.config.error.exception;

import study.hyeonu.config.error.ErrorCode;

public class ArticleNotFoundException extends NotFoundException{
    public ArticleNotFoundException(){
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }
}
