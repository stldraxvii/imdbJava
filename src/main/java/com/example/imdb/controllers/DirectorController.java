package com.example.imdb.controllers;

import com.example.imdb.models.Director;
import com.example.imdb.models.Film;
import com.example.imdb.models.data.DirectorDao;
import com.example.imdb.models.data.FilmDao;
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
@RequestMapping(value="director")
public class DirectorController {
    @Autowired
    private DirectorDao directorDao;
    @Autowired
    private FilmDao filmDao;

    @RequestMapping(method= RequestMethod.GET)
    public String index (Model model) {
        Iterable<Director> directors = directorDao.findAllByOrderByNameAsc();
        model.addAttribute("title","List of Directors");
        model.addAttribute("directors", directors);
        return"director/index";
    }

    @RequestMapping(value="view/{directorId}", method=RequestMethod.GET)
    public String viewDirector (Model model, @PathVariable int directorId) {
        Director director = directorDao.findOne(directorId);
        Iterable<Film> films = filmDao.findAllByOrderByAverageDesc();
        ArrayList<Film> filmsToShow = new ArrayList<>();

        for (Film film : films) {
            if (film.getDirectors().contains(director)) {
                filmsToShow.add(film);
            }
        }

        model.addAttribute("title", "id = " +directorId);
        model.addAttribute("films",filmsToShow);
        return("director/view");
    }
}
