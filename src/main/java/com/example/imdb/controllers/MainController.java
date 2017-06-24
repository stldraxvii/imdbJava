package com.example.imdb.controllers;

import com.example.imdb.models.data.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * Created by Matt on 6/17/2017.
 */
@Controller
public class MainController {
    @Autowired
    private FilmDao filmDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("films", filmDao.findAll());
        model.addAttribute("title", "Kemper Online Film Database");
        return "index";
    }

}
