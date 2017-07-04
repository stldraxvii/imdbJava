package com.example.imdb.models.data;

import com.example.imdb.models.Country;
import com.example.imdb.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Matt on 6/22/2017.
 */
@Repository
@Transactional
public interface CountryDao extends CrudRepository<Country, Integer> {
    public List<Country> findAllByOrderByNameAsc();
}
