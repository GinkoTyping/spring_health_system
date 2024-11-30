package com.dailyhealth.springhealthsystem.controller;

import com.dailyhealth.springhealthsystem.service.HealthMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/health-metrics")
public class HealthMetricsController {
    private final HealthMetricsService healthMetricsService;

    @Autowired
    public HealthMetricsController(HealthMetricsService healthMetricsService) {
        this.healthMetricsService = healthMetricsService;
    }

    @GetMapping("/update/{id}")
    public String redirectToEdit(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("id", id);
        return "edit-metrics";
    }

    @PostMapping("/update/{id}")
    public String updateHealthMetrics(@PathVariable("id") int id, @RequestParam("metricTypeId") int metricTypeId, @RequestParam("value") Double value, Model model) {
        int output = healthMetricsService.updateHealthMetrics(id, metricTypeId, value);
        if (output > 0) {
            return "redirect:/home";
        }
        model.addAttribute("message", "数据类型ID错误!");
        return "edit-metrics";
    }
}
