package kr.co.hyeonuj;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoParameterAjaxRestController {
    @RequestMapping("/get-with-no-parameter")
    public String getWithNoParameter(){
        return "파라미터 없는 Get 요청";
    }
}
