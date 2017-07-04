package com.example.imdb.controllers;

import com.example.imdb.models.Film;
import com.example.imdb.models.data.CountryDao;
import com.example.imdb.models.data.FilmDao;
import com.example.imdb.models.data.GenreDao;
import com.example.imdb.models.forms.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by Matt on 6/17/2017.
 */
@Controller
@RequestMapping(value="search")
public class SearchController {
    @Autowired
    private FilmDao filmdao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private CountryDao countryDao;

    @RequestMapping(method = RequestMethod.GET)
    public String displaySearchFilmForm (Model model) {
        model.addAttribute("title", "Search for a movie");
        model.addAttribute(new SearchForm());
        model.addAttribute("genres", genreDao.findAllByOrderByNameAsc());
        model.addAttribute("countries",countryDao.findAllByOrderByNameAsc());
        return("search");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String performSearch (Model model, @Valid SearchForm searchForm, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Serach for a movie");
            return "search";
        }
        Iterable<Film> allFilms = filmdao.findAllByOrderByAverageDesc();
        ArrayList<Film> filmsToReturn = new ArrayList<>();

        if (searchForm.getCountryId()==0 && searchForm.getGenreId()==0) {
            for (Film film : allFilms) {
                if (film.getName().contains(searchForm.getTitle()) && film.getYear().contains(searchForm.getYear())
                        && film.getDirectors().toString().contains(searchForm.getDirector())
                        && film.getActors().toString().contains(searchForm.getActor())) {
                    filmsToReturn.add(film);
                }
            }
        }
        if (searchForm.getCountryId()==0 && searchForm.getGenreId()!=0) {
            for (Film film : allFilms) {
                if (film.getName().contains(searchForm.getTitle()) && film.getYear().contains(searchForm.getYear())
                        && film.getDirectors().toString().contains(searchForm.getDirector())
                        && film.getActors().toString().contains(searchForm.getActor())
                        && film.getGenres().contains(genreDao.findOne(searchForm.getGenreId()))) {
                    filmsToReturn.add(film);
                }
            }
        }
        if (searchForm.getCountryId()!=0 && searchForm.getGenreId()==0) {
            for (Film film : allFilms) {
                if (film.getName().contains(searchForm.getTitle()) && film.getYear().contains(searchForm.getYear())
                        && film.getDirectors().toString().contains(searchForm.getDirector())
                        && film.getActors().toString().contains(searchForm.getActor())
                        && film.getCountries().contains(countryDao.findOne(searchForm.getCountryId()))) {
                    filmsToReturn.add(film);
                }
            }
        }
        if (searchForm.getCountryId()!=0 && searchForm.getGenreId()!=0) {
            for (Film film : allFilms) {
                if (film.getName().contains(searchForm.getTitle()) && film.getYear().contains(searchForm.getYear())
                        && film.getDirectors().toString().contains(searchForm.getDirector())
                        && film.getActors().toString().contains(searchForm.getActor())
                        && film.getGenres().contains(genreDao.findOne(searchForm.getGenreId()))
                        && film.getCountries().contains(countryDao.findOne(searchForm.getCountryId()))) {
                    filmsToReturn.add(film);
                }
            }
        }
        model.addAttribute("title","Search Results");
        model.addAttribute("films",filmsToReturn);
        return "results";
    }
}

