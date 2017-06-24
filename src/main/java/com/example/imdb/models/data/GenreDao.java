package com.example.imdb.models.data;

import com.example.imdb.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Matt on 6/23/2017.
 */
@Repository
@Transactional
public interface GenreDao extends CrudRepository<Genre,Integer> {
}
