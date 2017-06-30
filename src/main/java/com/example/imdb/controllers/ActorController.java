package com.example.imdb.controllers;

import com.example.imdb.models.Actor;
import com.example.imdb.models.Film;
import com.example.imdb.models.data.ActorDao;
import com.example.imdb.models.data.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Created by Matt on 6/23/2017.
 */
@Controller
@RequestMapping(value="actor")
public class ActorController {
    @Autowired
    private ActorDao actorDao;
    @Autowired
    private FilmDao filmDao;

    @RequestMapping(method= RequestMethod.GET)
    public String index (Model model) {
        Iterable<Actor> actors = actorDao.findAll();
        model.addAttribute("title","Actors");
        model.addAttribute("actors",actors);
        return"actor/index";
    }

    @RequestMapping(value="view/{actorId}", method=RequestMethod.GET)
    public String viewActor (Model model, @PathVariable int actorId) {
        Actor actor = actorDao.findOne(actorId);
        Iterable<Film> films = filmDao.findAllByOrderByAverageDesc();
        ArrayList<Film> filmsToShow = new ArrayList<>();

        for (Film film : films) {
            if (film.getActors().contains(actor)) {
                filmsToShow.add(film);
            }
        }

        model.addAttribute("title", "id = "+actorId);
        model.addAttribute("films", filmsToShow);
        return("actor/view");
    }
}
