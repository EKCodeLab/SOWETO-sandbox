package org.launchcode.soweto.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Concert extends AbstractEntity {

    @ManyToMany
    private List<Artist> artists = new ArrayList<>();

    @ManyToOne
    private Venue venue;

    public Concert() {
    }

    public Concert(Venue venue, List<Artist> artists) {
        super();
        this.venue = venue;
        this.artists = artists;
    }

    // Getters and setters.


    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
