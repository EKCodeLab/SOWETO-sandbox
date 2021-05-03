package org.launchcode.soweto.controllers;

import org.launchcode.soweto.models.Concert;
import org.launchcode.soweto.models.data.VenueRepository;
import org.launchcode.soweto.models.data.ConcertRepository;
import org.launchcode.soweto.models.data.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.launchcode.soweto.models.ConcertData;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private ArtistRepository artistRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("venue", "Venue");
        columnChoices.put("artist", "Artist");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("venues", venueRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
        return "list";
    }

    @RequestMapping(value = "concerts")
    public String listconcertsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Concert> concerts;
        if (column.toLowerCase().equals("all")){
            concerts = concertRepository.findAll();
            model.addAttribute("title", "All concerts");
        } else {
            concerts = ConcertData.findByColumnAndValue(column, value, concertRepository.findAll());
            model.addAttribute("title", "concerts with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("concerts", concerts);

        return "list-concerts";
    }
}
