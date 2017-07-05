package com.example.imdb.models.data;

import com.example.imdb.models.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Matt on 6/23/2017.
 */
@Repository
@Transactional
public interface ActorDao extends CrudRepository<Actor, Integer> {
    public List<Actor> findAllByOrderByNameAsc();
}
