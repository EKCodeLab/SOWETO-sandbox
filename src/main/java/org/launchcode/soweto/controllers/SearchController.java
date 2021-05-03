package org.launchcode.soweto.controllers;

import org.launchcode.soweto.models.Concert;
import org.launchcode.soweto.models.ConcertData;
import org.launchcode.soweto.models.data.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import static org.launchcode.soweto.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ConcertRepository concertRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Concert> concerts;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            concerts = concertRepository.findAll();
        } else {
            concerts = ConcertData.findByColumnAndValue(searchType, searchTerm, concertRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "concerts with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("concerts", concerts);

        return "search";
    }
}