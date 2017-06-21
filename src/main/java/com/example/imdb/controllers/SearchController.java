package com.example.imdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Matt on 6/17/2017.
 */
@Controller
@RequestMapping(value="search")
public class SearchController {

    @RequestMapping(method = RequestMethod.GET)
    public String displaySearchFilmForm (Model model) {
        model.addAttribute("title", "Search for a movie");
        return("search");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String performSearch (@RequestParam String filmName) {
        return "redirect:";
    }
}

