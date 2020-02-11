package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value="results")
    public String displaySearchResults(String searchType, String searchTerm, Model model){
        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase().equals("")||searchTerm.toLowerCase().equals("all")){
            jobs=JobData.findAll();
            model.addAttribute("title", "All Jobs");
        }else{
            jobs=JobData.findByColumnAndValue(searchType,searchTerm);
            model.addAttribute("title", "Jobs with " + searchType + ": " + searchTerm);
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);
        return "search";
    }

}
