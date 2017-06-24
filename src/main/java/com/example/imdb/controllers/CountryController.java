package com.example.imdb.controllers;

import com.example.imdb.models.Country;
import com.example.imdb.models.data.CountryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Matt on 6/22/2017.
 */
@Controller
@RequestMapping(value="country")
public class CountryController {

    @Autowired
    private CountryDao countryDao;

    @RequestMapping(method= RequestMethod.GET)
    public String index (Model model) {
        Iterable<Country> countries = countryDao.findAll();
        model.addAttribute("title", "Countries");
        model.addAttribute("countries", countries);
        return"country/index";
    }

    @RequestMapping(value="view/{countryId}", method=RequestMethod.GET)
    public String viewCountry (Model model, @PathVariable int countryId) {
        Country country = countryDao.findOne(countryId);
        model.addAttribute("title", "id = " +countryId);
        model.addAttribute("country",country);
        return("country/view");
    }
}
