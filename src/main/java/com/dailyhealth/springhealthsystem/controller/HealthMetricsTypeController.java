package com.dailyhealth.springhealthsystem.controller;

import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import com.dailyhealth.springhealthsystem.service.HealthMetricsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("message", "更新健康数据类型失败!");
        return "edit-metrics-type";
    }

    @PostMapping("/delete/{id}")
    public String deleteHealthMetricsType(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        int output = healthMetricsTypeService.deleteHealthMetricsType(id);
        if (output == 0) {
            redirectAttributes.addAttribute("metricsTypeMessage", "删除数据类型失败，请检查该类型是否被引用。");
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
