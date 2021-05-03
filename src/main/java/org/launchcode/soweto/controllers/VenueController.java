package org.launchcode.soweto.controllers;

import org.launchcode.soweto.models.Venue;
import org.launchcode.soweto.models.data.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("venues")
public class VenueController {

    @Autowired
    private VenueRepository venueRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("venues", venueRepository.findAll());
        return "venues/index";
    }

    @GetMapping("add")
    public String displayAddVenueForm(Model model) {
        model.addAttribute(new Venue());
        return "venues/add";
    }

    @PostMapping("add")
    public String processAddVenueForm(@ModelAttribute @Valid Venue newVenue,
                                      Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "venues/add";
        }

        venueRepository.save(newVenue);
        return "redirect:";
    }

    @GetMapping("view/{venueId}")
    public String displayViewVenue(Model model, @PathVariable int venueId) {

        Optional<Venue> result = venueRepository.findById(venueId);
        if (result.isPresent()) {
            Venue venue = result.get();
            model.addAttribute("venue", venue);
            return "venues/view";
        } else {
            return "redirect:../";
        }
    }
}
