package org.launchcode.soweto.models.data;

import org.launchcode.soweto.models.Venue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends CrudRepository<Venue, Integer> {

}
