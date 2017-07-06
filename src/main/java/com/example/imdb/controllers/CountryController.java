package com.example.imdb.controllers;

import com.example.imdb.models.Country;
import com.example.imdb.models.Film;
import com.example.imdb.models.data.CountryDao;
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
@RequestMapping(value="country")
public class CountryController {

    @Autowired
    private CountryDao countryDao;
    @Autowired
    private FilmDao filmDao;

    @RequestMapping(method= RequestMethod.GET)
    public String index (Model model) {
        Iterable<Country> countries = countryDao.findAllByOrderByNameAsc();
        ArrayList<String> averages = new ArrayList<>();
        for (Country country : countries){
            double total = 0;
            int count = 0;
            for (Film film : country.getFilms()) {
                total += film.getAverage();
                count += 1;
            }
            String average = Double.toString(Film.round((total/count),3));
            averages.add(average);
        }
        model.addAttribute("title", "List of Countries");
        model.addAttribute("averages",averages);
        model.addAttribute("countries", countries);
        return"country/index";
    }

    @RequestMapping(value="view/{countryId}", method=RequestMethod.GET)
    public String viewCountry (Model model, @PathVariable int countryId) {
        Country country = countryDao.findOne(countryId);
        Iterable<Film> films = filmDao.findAllByOrderByAverageDesc();
        ArrayList<Film> filmsToShow = new ArrayList<>();
        for (Film film : films) {
            if (film.getCountries().contains(country)) {
                filmsToShow.add(film);
            }
        }
        model.addAttribute("title", "id = " +countryId);
        model.addAttribute("films",filmsToShow);
        return("country/view");
    }
}
