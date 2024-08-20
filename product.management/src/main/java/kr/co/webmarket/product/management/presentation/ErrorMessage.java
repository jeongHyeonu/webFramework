package kr.co.webmarket.product.management.presentation;

import java.util.List;

public class ErrorMessage {
    private List<String> err;

    public ErrorMessage(List<String>errors){
        this.err = errors;
    }
    public List<String> getErr(){
        return err;
    }
}
