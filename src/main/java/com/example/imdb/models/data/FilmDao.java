package com.example.imdb.models.data;

import com.example.imdb.models.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Matt on 6/21/2017.
 */
@Repository
@Transactional
public interface FilmDao extends CrudRepository<Film, Integer>{
    public List<Film> findAllByOrderByAverageDesc();
}
