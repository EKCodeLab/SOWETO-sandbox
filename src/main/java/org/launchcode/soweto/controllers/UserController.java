package org.launchcode.soweto.controllers;

import org.launchcode.soweto.models.Artist;
import org.launchcode.soweto.models.Concert;
import org.launchcode.soweto.models.Venue;
import org.launchcode.soweto.models.data.VenueRepository;
import org.launchcode.soweto.models.data.ConcertRepository;
import org.launchcode.soweto.models.data.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class UserController {

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ConcertRepository concertRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My concerts");
        model.addAttribute("concerts", concertRepository.findAll());

        return "index";
    }

    @GetMapping("add")
    public String displayAddConcertForm(Model model) {
        model.addAttribute("title", "Add Concert");
        model.addAttribute(new Concert());
        model.addAttribute("venues", venueRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddConcertForm(@ModelAttribute @Valid Concert newConcert,
                                        Errors errors, Model model, @RequestParam int venueId, @RequestParam List<Integer> artists) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Concert");
            return "add";
        }
        List<Artist> artistObjs = (List<Artist>) artistRepository.findAllById(artists);
        Optional<Venue> result = venueRepository.findById(venueId);
        if (result.isEmpty()) {
            model.addAttribute("title", "Add Concert");
            return "add";
        } else {
            Venue venue = result.get();
            newConcert.setVenue(venue);
        }
        newConcert.setArtists(artistObjs);
        concertRepository.save(newConcert);
        model.addAttribute("concerts", concertRepository.findAll());
        return "redirect:";
    }

    @GetMapping("view/{concertId}")
    public String displayViewConcert(Model model, @PathVariable int concertId) {
        Optional<Concert> result = concertRepository.findById(concertId);
        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Concert Id: " + concertId);
            return "redirect:";
        } else {
            Concert concert = result.get();
            model.addAttribute("concert", concert);
            return "view";
        }

    }


}
