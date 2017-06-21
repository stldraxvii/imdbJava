package com.example.imdb.controllers;

import com.example.imdb.models.Film;
import com.example.imdb.models.FilmData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


/**
 * Created by Matt on 6/17/2017.
 */
@Controller
public class MainController {
    ArrayList<Film> films = FilmData.getAll();

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("films", films);
        model.addAttribute("title", "Kemper Online Film Database");
        return "index";
    }

}
