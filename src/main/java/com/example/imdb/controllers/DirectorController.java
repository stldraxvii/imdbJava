package com.example.imdb.controllers;

import com.example.imdb.models.Director;
import com.example.imdb.models.data.DirectorDao;
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
@RequestMapping(value="director")
public class DirectorController {
    @Autowired
    private DirectorDao directorDao;

    @RequestMapping(method= RequestMethod.GET)
    public String index (Model model) {
        Iterable<Director> directors = directorDao.findAll();
        model.addAttribute("title","Directors");
        model.addAttribute("directors", directors);
        return"director/index";
    }

    @RequestMapping(value="view/{directorId}", method=RequestMethod.GET)
    public String viewDirector (Model model, @PathVariable int directorId) {
        Director director = directorDao.findOne(directorId);
        model.addAttribute("title", "id = " +directorId);
        model.addAttribute("director",director);
        return("director/view");
    }
}
