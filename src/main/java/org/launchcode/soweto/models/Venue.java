package org.launchcode.soweto.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venue extends AbstractEntity {

    @NotNull
    @Size(min = 1, max = 100, message = "Location must be 1-100 characters long")
    private String location;

    @OneToMany
    @JoinColumn
    private final List<Concert> concerts = new ArrayList<>();

    public Venue() {
    }

    public Venue(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
