package com.dailyhealth.springhealthsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "World");
        model.addAttribute("message", "This is a message from the controller.");
        return "login";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute("user") User user, Model model) {
        // 处理表单提交，例如保存到数据库或执行其他逻辑
        // 这里只是简单地将用户信息添加到模型中以便在页面上显示（通常不会这样做，因为会导致重复提交问题）
        // 更好的做法是将用户重定向到另一个页面或显示一个确认消息
        model.addAttribute("user", user);
        return "result"; // 假设你有一个名为result.jsp的模板来显示提交结果
    }

    // 假设你有一个简单的User类来接收表单数据
    public static class User {
        private String username;
        private String email;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
