package kr.ac.hansung.cse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLogin(@RequestParam(value="error", required = false) String error,
                            @RequestParam(value="logout", required = false) String logout,
                            Model model) {

        if(error != null) {
            System.out.println(error);
            model.addAttribute("errorMsg","이름 또는 비밀번호를 찾을 수 없습니다.");
        }

        if(logout != null) {
            model.addAttribute("logoutMsg", "로그아웃이 완료되었습니다.");
        }

        return "login";
    }
}
