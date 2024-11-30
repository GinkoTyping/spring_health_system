package com.dailyhealth.springhealthsystem.controller;

import com.dailyhealth.springhealthsystem.model.User;
import com.dailyhealth.springhealthsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String defaultPage(Model model) {
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "username") String username, Model model) {
        model.addAttribute("username", username);
        return "home";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        User user = loginService.login(username, password);
        if (user != null) {
            redirectAttributes.addAttribute("username", user.getUsername());
            return "redirect:/home";
        } else {
            // 登录失败，返回登录页面并显示错误消息
            model.addAttribute("message", "Invalid username or password");
            return "login";
        }
    }
}
