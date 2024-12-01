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
        model.addAttribute("metricsTypeMessage", "数据类型ID错误!");
        return "edit-metrics";
    }

    @PostMapping("/delete/{id}")
    public String deleteHealthMetrics(@PathVariable("id") int id, Model model) {
        int output = healthMetricsTypeService.deleteHealthMetricsType(id);
        if (output == 0) {
            model.addAttribute("metricsTypeMessage", "删除失败");
        }
        return "redirect:/home";
    }

    @GetMapping("/add")
    public String redirectToAdd() {
        return "add-metrics-type";
    }

    @PostMapping("/add")
    public String addHealthMetrics(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("unit") String unit, Model model) {
        int output = healthMetricsTypeService.insertHealthMetricsType(name, description, unit);
        if (output > 0) {
            return "redirect:/home";
        }

        model.addAttribute("metricsMessage", "新增健康类型数据失败。");
        return "add-metrics-type";
    }
}
