package com.dailyhealth.springhealthsystem.controller;

import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import com.dailyhealth.springhealthsystem.service.HealthMetricsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/health-metrics-type")
public class HealthMetricsTypeController {
    private final HealthMetricsTypeService healthMetricsTypeService;

    @Autowired
    public HealthMetricsTypeController(HealthMetricsTypeService healthMetricsTypeService) {
        this.healthMetricsTypeService = healthMetricsTypeService;
    }

    @GetMapping("/update/{id}")
    public String redirectToEdit(@PathVariable(value = "id") int id, Model model) {
        HealthMetricsType healthMetricsType = healthMetricsTypeService.getHealthMetricsTypeById(id);
        model.addAttribute("id", id);
        model.addAttribute("healthMetricsType", healthMetricsType);
        return "edit-metrics-type";
    }

    @PostMapping("/update/{id}")
    public String updateHealthMetrics(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("unit") String unit, Model model) {
        int output = healthMetricsTypeService.updateHealthMetricsType(id, name, description, unit);
        if (output > 0) {
            return "redirect:/home";
        }
        model.addAttribute("message", "数据类型ID错误!");
        return "edit-metrics";
    }
}