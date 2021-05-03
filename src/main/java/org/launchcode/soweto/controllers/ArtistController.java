package org.launchcode.soweto.controllers;

import org.launchcode.soweto.models.Artist;
import org.launchcode.soweto.models.data.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("artists", artistRepository.findAll());
        return "artists/index";
    }

    @GetMapping("add")
    public String displayAddArtistForm(Model model) {
        model.addAttribute(new Artist());
        return "artists/add";
    }

    @PostMapping("add")
    public String processAddArtistForm(@ModelAttribute @Valid Artist newArtist, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "artists/add";
        }

        artistRepository.save(newArtist);
        return "redirect:";
    }

    @GetMapping("view/{artistId}")
    public String displayViewArtist(Model model, @PathVariable int artistId) {
        Optional<Artist> result = artistRepository.findById(artistId);
        if (result.isPresent()) {
            Artist artist = result.get();
            model.addAttribute("artist", artist);
            return "artists/view";
        } else {
            return "redirect:../";
        }
    }
}



