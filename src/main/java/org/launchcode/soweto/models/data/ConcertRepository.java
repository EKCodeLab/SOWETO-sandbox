package org.launchcode.soweto.models.data;

import org.launchcode.soweto.models.Concert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ConcertRepository extends CrudRepository<Concert, Integer> {
}
