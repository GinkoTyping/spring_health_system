package com.dailyhealth.springhealthsystem.controller;

import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.service.HealthMetricsService;
import com.dailyhealth.springhealthsystem.service.HealthMetricsTypeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/health-metrics")
public class HealthMetricsController {
    private final HealthMetricsService healthMetricsService;
    private final HealthMetricsTypeService healthMetricsTypeService;


    @Autowired
    public HealthMetricsController(HealthMetricsService healthMetricsService, HealthMetricsTypeService healthMetricsTypeService) {
        this.healthMetricsService = healthMetricsService;
        this.healthMetricsTypeService = healthMetricsTypeService;
    }

    @GetMapping("/update/{id}")
    public String redirectToEdit(@PathVariable(value = "id") int id, Model model) {
        HealthMetrics healthMetrics = healthMetricsService.getHealthMetricsById(id);
        boolean disableTypeInput = healthMetricsTypeService.isUniqueByMetricsTypeId(healthMetrics.getMetricTypeId());
        model.addAttribute("id", id);
        model.addAttribute("healthMetrics", healthMetrics);
        model.addAttribute("disableTypeInput", disableTypeInput);

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

    @PostMapping("/delete/{id}/{typeId}")
    public String deleteHealthMetrics(@PathVariable("id") int id, @PathVariable("typeId") int typeId, RedirectAttributes redirectAttributes) {
        int output = healthMetricsService.deleteHealthMetrics(id, typeId);
        if (output == 0) {
            redirectAttributes.addAttribute("metricsMessage", "删除健康数据失败，基础数据不允许删除。");
        }
        return "redirect:/home";
    }

    @GetMapping("/add")
    public String redirectToAdd(@RequestParam(value = "message", required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "add-metrics";
    }

    @PostMapping("/add")
    public String addHealthMetrics(HttpSession session, @RequestParam("metricTypeId") int metricTypeId, @RequestParam("value") Double value, RedirectAttributes redirectAttributes) {
        int userId = (int) session.getAttribute("id");
        int output = healthMetricsService.insertHealthMetrics(userId, metricTypeId, value);
        if (output > 0) {
            return "redirect:/home";
        }

        redirectAttributes.addAttribute("message", "新增健康数据失败，请检查类型ID。");
        return "redirect:/health-metrics/add";
    }
}
