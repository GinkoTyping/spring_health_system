package com.dailyhealth.springhealthsystem.controller;

import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.model.User;
import com.dailyhealth.springhealthsystem.service.HealthMetricsService;
import com.dailyhealth.springhealthsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LoginController {
    private final LoginService loginService;
    private final HealthMetricsService healthMetricsService;


    @Autowired
    public LoginController(LoginService loginService, HealthMetricsService healthMetricsService) {
        this.loginService = loginService;
        this.healthMetricsService = healthMetricsService;
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
    public ModelAndView processLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, RedirectAttributes redirectAttributes) {

        User user = loginService.login(username, password);
        if (user != null) {
            redirectAttributes.addAttribute("username", user.getUsername());
            List<HealthMetrics> healthMetricsList = healthMetricsService.getHealthMetricsByUserId(user.getId());

            ModelAndView modelAndView = new ModelAndView("home");
            modelAndView.addObject("username", user.getUsername());
            modelAndView.addObject("list", healthMetricsList);

            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("message", "Invalid username or password");
            return modelAndView;
        }
    }
}
