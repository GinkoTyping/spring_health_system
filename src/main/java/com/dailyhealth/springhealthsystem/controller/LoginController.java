package com.dailyhealth.springhealthsystem.controller;

import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import com.dailyhealth.springhealthsystem.model.User;
import com.dailyhealth.springhealthsystem.service.HealthMetricsService;
import com.dailyhealth.springhealthsystem.service.HealthMetricsTypeService;
import com.dailyhealth.springhealthsystem.service.LoginService;
import jakarta.servlet.http.HttpSession;
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
    private final HealthMetricsTypeService healthMetricsTypeService;


    @Autowired
    public LoginController(LoginService loginService, HealthMetricsService healthMetricsService, HealthMetricsTypeService healthMetricsTypeService) {
        this.loginService = loginService;
        this.healthMetricsService = healthMetricsService;
        this.healthMetricsTypeService = healthMetricsTypeService;
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
    public String home(@RequestParam(value = "metricsTypeMessage", required = false) String metricsTypeMessage, @RequestParam(value = "metricsMessage", required = false) String metricsMessage, Model model, HttpSession session) {
        int id = (int) session.getAttribute("id");
        String username = (String) session.getAttribute("username");

        List<HealthMetrics> healthMetricsList = healthMetricsService.getHealthMetricsByUserId(id);
        List<HealthMetricsType> healthMetricsTypeList = healthMetricsTypeService.getHealthMetricsTypesList();
        model.addAttribute("username", username);
        model.addAttribute("metricsList", healthMetricsList);
        model.addAttribute("metricsTypeList", healthMetricsTypeList);
        model.addAttribute("metricsMessage", metricsMessage);
        model.addAttribute("metricsTypeMessage", metricsTypeMessage);

        return "home";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        User user = loginService.login(username, password);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("id", user.getId());
            return "redirect:/home";
        } else {
            model.addAttribute("message", "账户或者密码错误。");
            return "login";
        }
    }
}
