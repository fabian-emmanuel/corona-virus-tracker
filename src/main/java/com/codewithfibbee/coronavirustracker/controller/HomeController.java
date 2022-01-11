package com.codewithfibbee.coronavirustracker.controller;

import com.codewithfibbee.coronavirustracker.models.LocationStats;
import com.codewithfibbee.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CoronaVirusDataService service;

    @Autowired
    public HomeController(CoronaVirusDataService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = this.service.getAllStats();
        int totalConfirmedCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        model.addAttribute("location_stats", allStats);
        model.addAttribute("total_confirmed_cases", totalConfirmedCases);
        return "home";
    }
}
