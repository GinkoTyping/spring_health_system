package com.dailyhealth.springhealthsystem.controller;

import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.service.HealthMetricsService;
import com.dailyhealth.springhealthsystem.service.HealthMetricsTypeService;
import jakarta.servlet.http.HttpSession;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/advice")
    public String getAdviceBIM(RedirectAttributes redirectAttributes) {
        String metricsMessage = healthMetricsService.getAdviceBMI();
        redirectAttributes.addAttribute("metricsMessage", metricsMessage);

        return "redirect:/home";
    }

    @GetMapping("/search-table")
    public String redirectToSearchType(@RequestParam(value = "metricsTypeId", required = false) Integer metricsTypeId, @RequestParam(value = "title") String title, @RequestParam(value = "username", required = false) String username, Model model) {
        List<HealthMetrics> healthMetricsList = new ArrayList<>();
        if (metricsTypeId != null) {
            healthMetricsList = healthMetricsService.getHealthMetricsByTypeId(metricsTypeId);
        } else if (!username.isEmpty()) {
            healthMetricsList = healthMetricsService.getHealthMetricsByUserName(username);
        }

        model.addAttribute("metricsList", healthMetricsList);
        model.addAttribute("title", title);

        return "search-table";
    }

    @PostMapping("/search-type")
    public String redirectToSearch(@RequestParam("metricsTypeId") Integer metricsTypeId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("metricsTypeId", metricsTypeId);
        redirectAttributes.addAttribute("username", null);
        redirectAttributes.addAttribute("title", "按照健康数据类型查询");

        return "redirect:/health-metrics/search-table";
    }

    @PostMapping("/search-username")
    public String redirectToSearch(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("username", username);
        redirectAttributes.addAttribute("metricsTypeId", null);
        redirectAttributes.addAttribute("title", "按照用户名称查询");

        return "redirect:/health-metrics/search-table";
    }
}
