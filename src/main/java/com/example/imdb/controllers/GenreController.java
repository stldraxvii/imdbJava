package com.example.imdb.controllers;

import com.example.imdb.models.Film;
import com.example.imdb.models.Genre;
import com.example.imdb.models.data.FilmDao;
import com.example.imdb.models.data.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Created by Matt on 6/22/2017.
 */
@Controller
@RequestMapping(value="genre")
public class GenreController {
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private FilmDao filmDao;

    @RequestMapping(method= RequestMethod.GET)
    public String index (Model model) {
        Iterable<Genre> genres  = genreDao.findAllByOrderByNameAsc();
        model.addAttribute("title","Genres");
        model.addAttribute("genres", genres);
        return"genre/index";
    }

    @RequestMapping(value="view/{genreId}", method=RequestMethod.GET)
    public String viewGenre (Model model, @PathVariable int genreId) {
        Genre genre = genreDao.findOne(genreId);
        Iterable<Film> films = filmDao.findAllByOrderByAverageDesc();
        ArrayList<Film> filmsToShow = new ArrayList<>();

        for (Film film : films) {
            if (film.getGenres().contains(genre)) {
                filmsToShow.add(film);
            }
        }

        model.addAttribute("title", "id = " +genreId);
        model.addAttribute("films",filmsToShow);
        return("genre/view");
    }
}
