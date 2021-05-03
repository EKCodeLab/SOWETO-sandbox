package org.launchcode.soweto.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Artist extends AbstractEntity {

    @NotNull
    @Size(min = 1, max = 100, message = "Description must be 1-100 characters long")
    private String description;

    @ManyToMany(mappedBy = "artists")
    private final List<Concert> concerts = new ArrayList<>();

    public Artist() {
    }

    public Artist(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}