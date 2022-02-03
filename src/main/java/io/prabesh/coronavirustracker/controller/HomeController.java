package io.prabesh.coronavirustracker.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.prabesh.coronavirustracker.models.LocationStats;
import io.prabesh.coronavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {

    private final CoronaVirusDataService coronaVirusDataService;

    public HomeController(CoronaVirusDataService coronaVirusDataService) {
        this.coronaVirusDataService = coronaVirusDataService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPrevDay).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
