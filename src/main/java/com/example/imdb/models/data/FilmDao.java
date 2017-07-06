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
    public List<Film> findTop50ByOrderByAverageDesc();
    public List<Film> findTop100ByOrderByAverageDesc();
    public List<Film> findTop250ByOrderByAverageDesc();

    public List<Film> findAllByOrderByAverageAsc();
    public List<Film> findTop100ByOrderByAverageAsc();
    public List<Film> findTop250ByOrderByAverageAsc();

    public List<Film> findAllByOrderByYearAsc();
    public List<Film> findAllByOrderByYearDesc();

    public List<Film> findAllByOrderByNameAsc();
}
