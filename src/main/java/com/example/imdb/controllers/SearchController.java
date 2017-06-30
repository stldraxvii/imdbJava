package com.example.imdb.controllers;

import com.example.imdb.models.Film;
import com.example.imdb.models.data.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by Matt on 6/17/2017.
 */
@Controller
@RequestMapping(value="search")
public class SearchController {
    @Autowired
    private FilmDao filmdao;

    @RequestMapping(method = RequestMethod.GET)
    public String displaySearchFilmForm (Model model) {
        model.addAttribute("title", "Search for a movie");
        return("search");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String performSearch (Model model, @RequestParam String title) {
        Iterable<Film> films = filmdao.findAll();
        ArrayList<Film> filmsToReturn = new ArrayList<>();
        for (Film film: films) {
            if (film.getName().contains(title)) {
                filmsToReturn.add(film);
            }
        }
        model.addAttribute("title","Search Results");
        model.addAttribute("films",filmsToReturn);
        return "results";
    }
}

